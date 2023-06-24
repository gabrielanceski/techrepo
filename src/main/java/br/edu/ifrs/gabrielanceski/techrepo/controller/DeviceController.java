package br.edu.ifrs.gabrielanceski.techrepo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.gabrielanceski.techrepo.dto.DeviceDTO;
import br.edu.ifrs.gabrielanceski.techrepo.model.Device;
import br.edu.ifrs.gabrielanceski.techrepo.service.DeviceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;
    
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
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
    public ResponseEntity<Device> save(@RequestBody @Valid DeviceDTO deviceDTO) {
        Device device = deviceService.save(deviceDTO);
        return ResponseEntity.ok(device);
    }

    @PatchMapping("{deviceId}")
    public ResponseEntity<Device> update(@PathVariable("deviceId") Long id, @RequestBody @Valid DeviceDTO deviceDTO) {
        Device device = deviceService.update(id, deviceDTO);
        return ResponseEntity.ok(device);
    }
}