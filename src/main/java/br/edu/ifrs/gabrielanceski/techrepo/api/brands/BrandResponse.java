package br.edu.ifrs.gabrielanceski.techrepo.api.brands;

public record BrandResponse(
    Long id,
    String name,
    Long devices,
    Long issues
) {
    
}
