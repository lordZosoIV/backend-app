package com.flatrock.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.flatrock.authservice.util.JwtClaim;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    private Algorithm signAlgorithm;
    private JWTVerifier verifier;

    @Value("${auth.security.jwt.secret}")
    private String secret;
    @Value("${auth.security.jwt.expiration}")
    private Long expTime;


    @PostConstruct
    protected void init() {
        signAlgorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(signAlgorithm).build();
    }

    public String generateAccessToken(String sub, List<String> roles) {
        return JWT.create()
                .withSubject(sub)
                .withClaim(JwtClaim.ROLES.value, roles)
                .withExpiresAt(Instant.now().plusSeconds(expTime * 60))
                .sign(signAlgorithm);
    }

    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }

}