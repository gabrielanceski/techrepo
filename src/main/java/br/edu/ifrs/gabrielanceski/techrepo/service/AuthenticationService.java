package br.edu.ifrs.gabrielanceski.techrepo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifrs.gabrielanceski.techrepo.api.auth.AuthenticationRequest;
import br.edu.ifrs.gabrielanceski.techrepo.api.auth.AuthenticationResponse;
import br.edu.ifrs.gabrielanceski.techrepo.api.auth.RegisterRequest;
import br.edu.ifrs.gabrielanceski.techrepo.jwt.JwtService;
import br.edu.ifrs.gabrielanceski.techrepo.model.User;
import br.edu.ifrs.gabrielanceski.techrepo.repository.RoleRepository;
import br.edu.ifrs.gabrielanceski.techrepo.repository.UserRepository;
import io.jsonwebtoken.lang.Maps;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(roleRepository.findByName("ROLE_USER"))
                .build();
        userRepository.save(user);
        
        Map<String, Object> extraClaims = Maps.of("id", (Object) user.getId()).build();
        String token = jwtService.generateToken(extraClaims, user);
        AuthenticationResponse response = new AuthenticationResponse(token);
        return response;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        Map<String, Object> extraClaims = Maps.of("id", (Object) user.getId()).build();
        String token = jwtService.generateToken(extraClaims, user);
        AuthenticationResponse response = new AuthenticationResponse(token);
        return response;
    }

}