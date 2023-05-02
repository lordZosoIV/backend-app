package com.flatrock.authservice.controller;


import com.flatrock.authservice.model.request.UserLoginRequest;
import com.flatrock.authservice.model.request.UserRegistrationRequest;
import com.flatrock.authservice.model.response.UserAuthResponse;
import com.flatrock.authservice.service.AuthService;
import com.flatrock.authservice.service.facade.AuthServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceFacade service;

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserAuthResponse authResponse = service.login(request);
        return ResponseEntity.ok(authResponse);
    }



}