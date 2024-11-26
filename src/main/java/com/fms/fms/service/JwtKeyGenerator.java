package com.fms.fms.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Encoders;

import java.security.Key;

public class JwtKeyGenerator {

    public static void main(String[] args) {
        // Generate a new HMAC SHA-256 key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Encode the key as a Base64 string
        String encodedKey = Encoders.BASE64.encode(key.getEncoded());

        // Print the key
        System.out.println("Generated JWT Secret Key: " + encodedKey);
    }
}

