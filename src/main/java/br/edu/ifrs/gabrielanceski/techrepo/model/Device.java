package br.edu.ifrs.gabrielanceski.techrepo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "devices")
@Data
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false, length = 64)
    private String name;
    @Column(name = "model", nullable = false, unique = true, length = 128)
    private String model;
    @Column(name = "extra_info", nullable = true, length = 255)
    private String extraInfo;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
}