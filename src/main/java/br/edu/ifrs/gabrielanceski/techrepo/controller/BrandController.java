package br.edu.ifrs.gabrielanceski.techrepo.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.edu.ifrs.gabrielanceski.techrepo.dto.BrandDTO;
import br.edu.ifrs.gabrielanceski.techrepo.exception.BrandNotFoundException;
import br.edu.ifrs.gabrielanceski.techrepo.model.Brand;
import br.edu.ifrs.gabrielanceski.techrepo.service.BrandService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/brands")
public class BrandController {
    @Autowired
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> getBrands() {
        return brandService.findAll();
    }

    @GetMapping("{brandId}")
    public ResponseEntity<Brand> getBrand(@PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(brandService.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId)));
    }

    @PostMapping
    public ResponseEntity<?> saveBrand(@RequestBody @Valid BrandDTO brandDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        } else if (brandService.existsByName(brandDTO.name())) {
            return ResponseEntity.status(409).body("Marca já existente!");
        }

        Brand brand = new Brand();
        brand.setName(brandDTO.name());
        brandService.save(brand);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("{brandId}")
    public ResponseEntity<?> updateBrand(@PathVariable("brandId") Long brandId, @RequestBody @Valid BrandDTO brandDTO) {
        Brand brand = brandService.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId));
        brand.setName(brandDTO.name());
        brandService.save(brand);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("{brandId}")
    public ResponseEntity<?> deleteBrand(@PathVariable("brandId") Long brandId) {
        Brand brand = brandService.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId));
        brandService.delete(brandId);
        return ResponseEntity.ok(brand);
    }
}