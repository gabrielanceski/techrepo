package br.edu.ifrs.gabrielanceski.techrepo.controller;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;

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

import br.edu.ifrs.gabrielanceski.techrepo.dto.DeviceDTO;
import br.edu.ifrs.gabrielanceski.techrepo.model.Brand;
import br.edu.ifrs.gabrielanceski.techrepo.model.Device;
import br.edu.ifrs.gabrielanceski.techrepo.service.BrandService;
import br.edu.ifrs.gabrielanceski.techrepo.service.DeviceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/devices")
public class DeviceController {
    @Autowired
    private final DeviceService deviceService;
    @Autowired
    private final BrandService brandService;

    public DeviceController(DeviceService deviceService, BrandService brandService) {
        this.deviceService = deviceService;
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok(deviceService.findAll());
    }

    @GetMapping("{deviceId}")
    public ResponseEntity<?> getDevice(@PathVariable("deviceId") Long id) {
        Optional<Device> device = deviceService.findById(id);
        if (device.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado.");
        return ResponseEntity.ok(device.get());
    }

    @DeleteMapping("{deviceId}")
    public ResponseEntity<?> delete(@PathVariable("deviceId") Long id) {
        if (!deviceService.exists(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado.");
        deviceService.delete(id);
        return ResponseEntity.ok("Dispositivo removido.");
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid DeviceDTO deviceDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        Optional<Brand> brand = brandService.findById(deviceDTO.brandId());
        if (brand.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Marca inválida.");

        Device device = new Device();
        device.setName(deviceDTO.name());
        device.setModel(deviceDTO.model());
        device.setBrand(brand.get());
        device.setExtraInfo(deviceDTO.extraInfo());
        deviceService.save(device);
        return ResponseEntity.ok(device);
    }

    @PutMapping("{deviceId}")
    public ResponseEntity<?> update(@PathVariable("deviceId") Long id, @RequestBody @Valid DeviceDTO deviceDTO) {
        Optional<Device> deviceOpt = deviceService.findById(id);
        if (deviceOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado.");
        Optional<Brand> brand = brandService.findById(deviceDTO.brandId());
        if (brand.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Marca inválida.");
        Device device = deviceOpt.get();
        device.setId(id);
        device.setName(deviceDTO.name());
        device.setModel(deviceDTO.model());
        device.setBrand(brand.get());
        device.setExtraInfo(deviceDTO.extraInfo());
        deviceService.save(device);
        return ResponseEntity.ok(deviceOpt);
    }
}