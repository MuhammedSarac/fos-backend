package com.fms.fms.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fms.fms.dto.FamilyDto;
import com.fms.fms.entity.Family;
import com.fms.fms.repository.FamilyRepository;

@Service
public class AuthenticationService {
    private final FamilyRepository familyRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        FamilyRepository familyRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.familyRepository = familyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Family signup(FamilyDto input) {
        Family family = new Family();
        family.setFamilyName(input.getFamilyName());
        family.setEmail(input.getEmail());
        family.setPassword(passwordEncoder.encode(input.getPassword()));

        return familyRepository.save(family);
    }

    public Family authenticate(FamilyDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return familyRepository.findByEmail(input.getEmail())  // Corrected reference to familyRepository
                .orElseThrow();
    }
}
