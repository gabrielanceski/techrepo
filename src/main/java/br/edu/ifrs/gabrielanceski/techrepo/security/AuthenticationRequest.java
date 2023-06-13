package br.edu.ifrs.gabrielanceski.techrepo.security;

public record AuthenticationRequest(String email, String password) {}