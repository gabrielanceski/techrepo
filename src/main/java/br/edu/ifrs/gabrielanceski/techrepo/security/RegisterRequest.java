package br.edu.ifrs.gabrielanceski.techrepo.security;

public record RegisterRequest(String nickname, String email, String password) {}