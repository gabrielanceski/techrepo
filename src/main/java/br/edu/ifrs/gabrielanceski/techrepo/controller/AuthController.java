package br.edu.ifrs.gabrielanceski.techrepo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.gabrielanceski.techrepo.api.auth.AuthenticationRequest;
import br.edu.ifrs.gabrielanceski.techrepo.api.auth.AuthenticationResponse;
import br.edu.ifrs.gabrielanceski.techrepo.api.auth.RegisterRequest;
import br.edu.ifrs.gabrielanceski.techrepo.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
}