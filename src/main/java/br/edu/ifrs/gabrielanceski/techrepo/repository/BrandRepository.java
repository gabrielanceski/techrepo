package br.edu.ifrs.gabrielanceski.techrepo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.gabrielanceski.techrepo.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByName(String name);

}