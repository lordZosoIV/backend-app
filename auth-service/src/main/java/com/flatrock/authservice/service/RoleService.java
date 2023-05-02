package com.flatrock.authservice.service;

import com.flatrock.authservice.entity.RoleEntity;
import com.flatrock.authservice.entity.model.Role;
import com.flatrock.authservice.exception.HandledException;
import com.flatrock.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public RoleEntity getRoleByName(Role name) {
        return repository.findByName(name)
                .orElseThrow(() -> new HandledException("Role not found"));
    }

    public RoleEntity getRoleByName(String name) {
        return getRoleByName(Role.valueOf(name));
    }

    public RoleEntity getDefaultRole() {
        return getRoleByName(Role.CLIENT);
    }

}
