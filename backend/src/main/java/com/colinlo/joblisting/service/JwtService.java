package com.colinlo.joblisting.service;

import com.colinlo.joblisting.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(User user);

    <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    String generateToken(Map<String, Object> extractClaims, User user);

    boolean isTokenValid(String token, UserDetails userDetails);
}
