package com.flatrock.authservice.controller.admin;


import com.flatrock.authservice.entity.model.Role;
import com.flatrock.authservice.model.request.UserRegistrationRequest;
import com.flatrock.authservice.model.request.UserUpdateRequest;
import com.flatrock.authservice.model.response.UserInfoResponse;
import com.flatrock.authservice.model.response.UserResponse;
import com.flatrock.authservice.service.AdminService;
import com.flatrock.authservice.service.UserService;
import com.flatrock.authservice.service.facade.AdminServiceFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceFacade service;

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> getUser(@PathVariable("id") @Min(1) Long id) {
        UserInfoResponse user = service.getById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoResponse> updateUser(@PathVariable("id") Long id,
                                                           @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        UserInfoResponse user = service.update(id, userUpdateRequest);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<UserInfoResponse> updateUserRole(@PathVariable("id") @Min(1) Long id,
                                                               @RequestBody Role role) {
        UserInfoResponse user = service.updateRole(id, role);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") @Min(1) Long id) {
        service.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable("id") @Min(1) Long id) {
        service.activate(id);
        return ResponseEntity.ok().build();
    }


}