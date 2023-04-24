package br.edu.ifrs.gabrielanceski.techrepo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(long brandId) {
        super("Marca não encontrada! id=" + brandId);
    }
}