package br.edu.ifrs.gabrielanceski.techrepo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.gabrielanceski.techrepo.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    
}