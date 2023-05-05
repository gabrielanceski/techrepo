package br.edu.ifrs.gabrielanceski.techrepo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.gabrielanceski.techrepo.model.Device;
import br.edu.ifrs.gabrielanceski.techrepo.repository.DeviceRepository;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Optional<Device> findById(Long id) {
        return deviceRepository.findById(id);
    }

    public void save(Device device) {
        deviceRepository.save(device);
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return deviceRepository.existsById(id);
    }

    public List<Device> findByBrandId(Long brandId) {
        if (brandId <= 0) return Collections.emptyList();
        return deviceRepository.findAllByBrandId(brandId);
    }
}
