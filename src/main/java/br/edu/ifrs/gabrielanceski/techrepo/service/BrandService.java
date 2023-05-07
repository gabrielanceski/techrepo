package br.edu.ifrs.gabrielanceski.techrepo.service;

import br.edu.ifrs.gabrielanceski.techrepo.repository.BrandRepository;
import br.edu.ifrs.gabrielanceski.techrepo.dto.BrandDTO;
import br.edu.ifrs.gabrielanceski.techrepo.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final DeviceService deviceService;

    @Autowired
    public BrandService(BrandRepository brandRepository, DeviceService deviceService) {
        this.brandRepository = brandRepository;
        this.deviceService = deviceService;
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findById(Long brandId) {
        return brandRepository.findById(brandId);
    }

    public Brand save(BrandDTO dto) {
        if (existsByName(dto.name()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Marca já existente!");
        Brand brand = new Brand();
        brand.setName(dto.name());
        brandRepository.save(brand);
        return brand;
    }

    public Brand update(Long id, BrandDTO dto) {
        Brand brand = findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada!"));
        brand.setName(dto.name());
        brandRepository.save(brand);
        return brand;
    }

    public Brand delete(Long id) {
        if (id <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inválido!");
        Brand brand = findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada!"));
        if (deviceService.existsByBrandId(id))
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Esta marca possui dispositivos cadastrados!");
        brandRepository.deleteById(id);
        return brand;
    }

    public boolean existsByName(String name) {
        if (name == null || name.isEmpty() || name.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome inválido!");
        return brandRepository.existsByName(name);
    }

    public boolean existsById(Long id) {
        if (id <= 0)
            return false;
        return brandRepository.existsById(id);
    }
}