package com.fms.fms.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    // Extracting the username from the token
    public String extractUsername(String token) {
        String username = extractClaim(token, Claims::getSubject);
        logger.debug("Extracted username: {}", username);
        return username;
    }

    // Extract a specific claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Generate a token using user details
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Generate a token with extra claims
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        String token = buildToken(extraClaims, userDetails, jwtExpiration);
        logger.debug("Generated token: {}", token);
        return token;
    }

    // Return expiration time
    public long getExpirationTime() {
        return jwtExpiration;
    }

    // Build the JWT token
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        String token = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        // Log the generated token
        logger.debug("Token built successfully with expiration: {}", expiration);
        return token;
    }

    // Validate the token against the user details
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        boolean valid = (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        logger.debug("Is token valid: {}", valid);
        return valid;
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date from the token
    private Date extractExpiration(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        logger.debug("Token expiration: {}", expiration);
        return expiration;
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Log the extracted claims
            logger.debug("Extracted claims from token: {}", claims);
            return claims;
        } catch (Exception e) {
            // Log the exception if extraction fails
            logger.error("Failed to extract claims from token. Error: {}", e.getMessage());
            throw e;
        }
    }

    // Decode the secret key and return the signing key
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        // Log the key length and ensure it's the correct size for HMAC-SHA256
        logger.debug("Decoded secret key bytes length: {}", keyBytes.length);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
