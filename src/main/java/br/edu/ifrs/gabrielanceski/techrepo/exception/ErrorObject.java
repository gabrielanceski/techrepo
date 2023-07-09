package br.edu.ifrs.gabrielanceski.techrepo.exception;

import java.time.LocalDateTime;

public record ErrorObject(
    int status,
    LocalDateTime timestamp,
    String... messages
) {}
