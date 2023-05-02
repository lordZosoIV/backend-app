package com.flatrock.authservice.repository;

import com.flatrock.authservice.entity.RoleEntity;
import com.flatrock.authservice.entity.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(Role role);
}
