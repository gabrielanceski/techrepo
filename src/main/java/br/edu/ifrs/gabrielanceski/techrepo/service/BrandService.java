package br.edu.ifrs.gabrielanceski.techrepo.service;

import br.edu.ifrs.gabrielanceski.techrepo.repository.BrandRepository;
import br.edu.ifrs.gabrielanceski.techrepo.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findById(Long brandId) {
        return brandRepository.findById(brandId);
    }

    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    public void delete(long id) {
        brandRepository.deleteById(id);
    }

    public boolean existsByName(String name) {
        return brandRepository.existsByName(name);
    }
}