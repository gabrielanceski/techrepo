package br.edu.ifrs.gabrielanceski.techrepo.service;

import br.edu.ifrs.gabrielanceski.techrepo.repository.BrandRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.IssueRepository;
import br.edu.ifrs.gabrielanceski.techrepo.api.brands.BrandResponse;
import br.edu.ifrs.gabrielanceski.techrepo.dto.BrandDTO;
import br.edu.ifrs.gabrielanceski.techrepo.exception.ResourceAlreadyExistsException;
import br.edu.ifrs.gabrielanceski.techrepo.exception.ResourceNotFoundException;
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
    private final IssueRepository issueRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository, DeviceService deviceService, IssueRepository issueRepository) {
        this.brandRepository = brandRepository;
        this.deviceService = deviceService;
        this.issueRepository = issueRepository;
    }

    public List<BrandResponse> findAll() {
        return brandRepository.findAll().stream().map(brand -> new BrandResponse(
                brand.getId(),
                brand.getName(),
                deviceService.countByBrandId(brand.getId()),
                issueRepository.countByBrandId(brand.getId())
        )).toList();
    }

    public Optional<Brand> findById(Long brandId) {
        return brandRepository.findById(brandId);
    }

    public Brand save(BrandDTO dto) {
        if (existsByName(dto.name()))
            throw new ResourceAlreadyExistsException("Marca já cadastrada!");
        Brand brand = new Brand();
        brand.setName(dto.name());
        brandRepository.save(brand);
        return brand;
    }

    public Brand update(Long id, BrandDTO dto) {
        Brand brand = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada!"));
        brand.setName(dto.name());
        brandRepository.save(brand);
        return brand;
    }

    public Brand delete(Long id) {
        if (id <= 0)
            throw new ResourceNotFoundException("Marca não encontrada!");
        Brand brand = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada!"));
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