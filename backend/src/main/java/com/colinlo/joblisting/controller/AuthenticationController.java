package com.colinlo.joblisting.controller;

import com.colinlo.joblisting.model.AuthenticationRequest;
import com.colinlo.joblisting.model.AuthenticationResponse;
import com.colinlo.joblisting.model.RegisterRequest;
import com.colinlo.joblisting.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "${frontend.base.url}")
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/authenticate-token")
    public ResponseEntity<Boolean> authenticateToken(
            @RequestHeader String token
    ){
        return ResponseEntity.ok(authService.authenticateToken(token));
    }
}
