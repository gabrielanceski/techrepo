package br.edu.ifrs.gabrielanceski.techrepo.dto;

import jakarta.validation.constraints.NotBlank;

public record BrandDTO(@NotBlank(message = "O nome da marca é obrigatório.") String name) {}