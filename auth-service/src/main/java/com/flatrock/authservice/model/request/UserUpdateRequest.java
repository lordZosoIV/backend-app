package com.flatrock.authservice.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String address;

    @NotEmpty
    private String phoneNumber;

}
