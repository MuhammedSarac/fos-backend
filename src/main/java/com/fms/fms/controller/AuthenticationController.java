package com.fms.fms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.fms.dto.FamilyDto;
import com.fms.fms.entity.Family;
import com.fms.fms.responses.LoginResponse;
import com.fms.fms.service.AuthenticationService;
import com.fms.fms.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Family> register(@RequestBody FamilyDto familyDto) {
        Family registeredUser = authenticationService.signup(familyDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody FamilyDto familyDto) {
        Family authenticatedUser = authenticationService.authenticate(familyDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setFamilyName(authenticatedUser.getFamilyName());
        loginResponse.setEmail(authenticatedUser.getEmail());
        loginResponse.setId(authenticatedUser.getId());

        return ResponseEntity.ok(loginResponse);
    }
}
