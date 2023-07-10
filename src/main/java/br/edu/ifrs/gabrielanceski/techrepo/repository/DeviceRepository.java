package br.edu.ifrs.gabrielanceski.techrepo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.gabrielanceski.techrepo.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    
    @Query("SELECT d FROM devices d WHERE d.brand.id = ?1")
    List<Device> findAllByBrandId(Long brandId);

    @Query("SELECT COUNT(d) > 0 FROM devices d WHERE d.brand.id = ?1")
    boolean existsByBrandId(Long brandId);

    @Query("SELECT COUNT(d) > 0 FROM devices d WHERE d.name = ?1")
    boolean existsByName(String name);

    @Query("SELECT COUNT(d) FROM devices d WHERE d.brand.id = ?1")
    Long countByBrandId(Long brandId);

}