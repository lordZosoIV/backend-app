package com.flatrock.authservice.service;

import com.flatrock.authservice.entity.UserEntity;
import com.flatrock.authservice.entity.model.Role;
import com.flatrock.authservice.model.request.UserUpdateRequest;
import com.flatrock.authservice.model.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserService userService;
    private final RoleService roleService;


    public UserInfoResponse getById(Long id) {
        return UserInfoResponse.transform(userService.getById(id));
    }

    public UserInfoResponse update(Long id, UserUpdateRequest userUpdateRequest) {
        UserEntity user = userService.update(id, userUpdateRequest.getName(), userUpdateRequest.getEmail(), userUpdateRequest.getPassword());
        return UserInfoResponse.transform(user);
    }

    public UserInfoResponse updateRole(Long id, Role role) {
        UserEntity user = userService.updateRole(id, roleService.getRoleByName(role));
        return UserInfoResponse.transform(user);
    }

    public void deactivate(Long id) {
        userService.deactivate(id);
    }

    public void activate(Long id) {
        userService.activate(id);
    }
}