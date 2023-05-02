package com.flatrock.authservice.controller;


import com.flatrock.authservice.model.request.UserRegistrationRequest;
import com.flatrock.authservice.model.response.UserResponse;
import com.flatrock.authservice.service.facade.UserServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceFacade service;

    @GetMapping
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserResponse user = service.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserRegistrationRequest request) {
        UserResponse user = service.update(request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete() {
        service.deactivate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}