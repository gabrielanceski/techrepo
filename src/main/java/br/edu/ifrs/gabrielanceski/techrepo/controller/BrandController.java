package br.edu.ifrs.gabrielanceski.techrepo.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifrs.gabrielanceski.techrepo.dto.BrandDTO;
import br.edu.ifrs.gabrielanceski.techrepo.model.Brand;
import br.edu.ifrs.gabrielanceski.techrepo.service.BrandService;
import br.edu.ifrs.gabrielanceski.techrepo.service.DeviceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/brands")
public class BrandController {
    private final BrandService brandService;
    private final DeviceService deviceService;

    @Autowired
    public BrandController(BrandService brandService, DeviceService deviceService) {
        this.brandService = brandService;
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getBrands() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("{brandId}")
    public ResponseEntity<Brand> getBrand(@PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(brandService.findById(brandId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Marca não encontrada!")));
    }

    @PostMapping
    public ResponseEntity<?> saveBrand(@RequestBody @Valid BrandDTO brandDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        } else if (brandService.existsByName(brandDTO.name())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Marca já existente!");
        }

        Brand brand = new Brand();
        brand.setName(brandDTO.name());
        brandService.save(brand);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("{brandId}")
    public ResponseEntity<?> updateBrand(@PathVariable("brandId") Long brandId, @RequestBody @Valid BrandDTO brandDTO) {
        Brand brand = brandService.findById(brandId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Marca não encontrada!"));
        brand.setName(brandDTO.name());
        brandService.save(brand);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("{brandId}")
    public ResponseEntity<?> deleteBrand(@PathVariable("brandId") Long brandId) {
        Brand brand = brandService.findById(brandId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Marca não encontrada!"));
        if (deviceService.existsByBrandId(brandId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Esta marca possui dispositivos cadastrados!");
        }
        brandService.delete(brandId);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("{brandId}/devices")
    public ResponseEntity<?> getDevices(@PathVariable("brandId") Long brandId) {
        if (!brandService.existsById(brandId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Marca não encontrada!");
        }
        return ResponseEntity.ok().body(deviceService.findByBrandId(brandId));
    }
}