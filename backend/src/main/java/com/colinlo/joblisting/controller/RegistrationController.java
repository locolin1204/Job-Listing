package com.colinlo.joblisting.controller;

import com.colinlo.joblisting.model.RegisterRequest;
import com.colinlo.joblisting.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(registrationService.registerUserAndCreateToken(request));
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @PostMapping("/resend-token")
    public String resendToken(@RequestBody RegisterRequest request){
        return registrationService.resendToken(request);
    }
}
