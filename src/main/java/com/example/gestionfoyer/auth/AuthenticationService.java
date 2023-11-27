package com.example.gestionfoyer.auth;

import com.example.gestionfoyer.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.gestionfoyer.user.Role;
import com.example.gestionfoyer.user.User;
import com.example.gestionfoyer.user.UserRepository;


import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    /*public  String generatePassword() {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char letter = (char) (random.nextInt(26) + 'A');
            randomString.append(letter);
        }
        for (int i = 0; i < 4; i++) {
            int digit = random.nextInt(10);
            randomString.append(digit);
        }
        System.out.println(randomString.toString());
        return randomString.toString();
    }*/


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}