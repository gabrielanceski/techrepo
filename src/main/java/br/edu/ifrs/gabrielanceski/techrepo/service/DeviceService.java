package br.edu.ifrs.gabrielanceski.techrepo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrs.gabrielanceski.techrepo.dto.DeviceDTO;
import br.edu.ifrs.gabrielanceski.techrepo.exception.ResourceAlreadyExistsException;
import br.edu.ifrs.gabrielanceski.techrepo.exception.ResourceNotFoundException;
import br.edu.ifrs.gabrielanceski.techrepo.model.Brand;
import br.edu.ifrs.gabrielanceski.techrepo.model.Device;
import br.edu.ifrs.gabrielanceski.techrepo.repository.BrandRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.DeviceRepository;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, BrandRepository brandRepository) {
        this.deviceRepository = deviceRepository;
        this.brandRepository = brandRepository;
    }

    public Optional<Device> findById(Long id) {
        return deviceRepository.findById(id);
    }

    public Device save(DeviceDTO dto) {
        if (deviceRepository.existsByName(dto.name()))
            throw new ResourceAlreadyExistsException("Dispositivo já cadastrado!");
        Brand brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada!"));
        Device device = new Device();
        device.setName(dto.name());
        device.setModel(dto.model());
        device.setBrand(brand);
        device.setExtraInfo(dto.extraInfo());
        deviceRepository.save(device);
        return device;
    }

    public Device update(Long id, DeviceDTO dto) {
        Device device = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositivo não encontrado!"));
        Brand brand = brandRepository.findById(dto.brandId())
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada!"));
        device.setName(dto.name());
        device.setModel(dto.model());
        device.setBrand(brand);
        device.setExtraInfo(dto.extraInfo());
        deviceRepository.save(device);
        return device;
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
        if (brandId <= 0)
            return Collections.emptyList();
        if (!brandRepository.existsById(brandId))
            throw new ResourceNotFoundException("Marca não encontrada!");
        return deviceRepository.findAllByBrandId(brandId);
    }

    public boolean existsByBrandId(Long brandId) {
        return deviceRepository.existsByBrandId(brandId);
    }
}