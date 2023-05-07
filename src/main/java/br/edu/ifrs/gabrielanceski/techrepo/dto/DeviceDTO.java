package br.edu.ifrs.gabrielanceski.techrepo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeviceDTO(
    @NotBlank(message = "O nome não foi especificado.") String name,
    @NotBlank(message = "O modelo não foi especificado.") String model,
    String extraInfo,
    @NotNull(message = "A marca não foi especificada.") Long brandId
) {}