package com.flatrcok.order.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    private JWTVerifier verifier;

    @Value("${auth.security.jwt.secret}")
    private String secret;

    @PostConstruct
    protected void init() {
        Algorithm signAlgorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(signAlgorithm)
                .build();
    }

    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        return verifier.verify(token);
    }

    public List<String> extractRoles(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("roles").asList(String.class);
    }

}