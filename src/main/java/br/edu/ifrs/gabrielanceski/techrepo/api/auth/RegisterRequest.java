package br.edu.ifrs.gabrielanceski.techrepo.api.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "O nome de usuário não pode estar em branco.") @Size(min = 5, message = "O nome de usuário deve possuir ao menos 5 caracteres.") String username,
        @NotNull @Email(message = "O e-mail não pode estar em branco.") String email,
        @NotBlank(message = "A senha não pode estar em branco.") @Size(min = 8, message = "A senha deve possuir ao menos 8 caracteres.") String password) {
}