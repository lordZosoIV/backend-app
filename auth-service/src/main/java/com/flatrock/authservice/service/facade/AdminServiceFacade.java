package com.flatrock.authservice.service.facade;

import com.flatrock.authservice.entity.model.Role;
import com.flatrock.authservice.model.request.UserUpdateRequest;
import com.flatrock.authservice.model.response.UserInfoResponse;
import com.flatrock.authservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceFacade {

    private final AdminService adminService;


    public UserInfoResponse getById(Long id) {
        return adminService.getById(id);
    }

    public UserInfoResponse update(Long id, UserUpdateRequest userUpdateRequest) {
        return adminService.update(id, userUpdateRequest);
    }

    public UserInfoResponse updateRole(Long id, Role role) {
        return adminService.updateRole(id, role);
    }

    public void deactivate(Long id) {
        adminService.deactivate(id);
    }

    public void activate(Long id) {
        adminService.activate(id);
    }
}