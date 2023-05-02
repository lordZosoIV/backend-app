package com.flatrock.authservice.model.response;

import com.flatrock.authservice.entity.RoleEntity;
import com.flatrock.authservice.entity.UserEntity;
import com.flatrock.authservice.entity.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfoResponse {
    private Long id;
    private String email;
    private String name;
    private Boolean active;
    private List<Role> roles;

    public static UserInfoResponse transform(UserEntity user) {
        return UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .active(user.getActive())
                .roles(user.getRoles().stream().map(RoleEntity::getName).toList())
                .build();
    }
}
