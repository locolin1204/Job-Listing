package com.colinlo.joblisting.service.impl;

import com.colinlo.joblisting.controller.JobController;
import com.colinlo.joblisting.model.*;
import com.colinlo.joblisting.repository.impl.UserRepositoryImpl;
import com.colinlo.joblisting.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private UserRepositoryImpl userRepo;
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.createUser(user);
        logger.info(String.format("Created User with firstname: %s, lastname: %s, email: %s", user.getFirstname(), user.getLastname(), user.getEmail()));

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    logger.error("User: %s not found", request.getEmail());
                    return new NoSuchElementException("User not found");
                });
        String jwtToken = jwtService.generateToken(user);
        logger.info(String.format("Authenticated and Generated JWT Token for User with email: %s", user.getEmail()));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public Boolean authenticateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(jwtService.getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}

