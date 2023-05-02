package com.flatrock.authservice.service.facade;

import com.flatrock.authservice.entity.UserEntity;
import com.flatrock.authservice.model.request.UserLoginRequest;
import com.flatrock.authservice.model.request.UserRegistrationRequest;
import com.flatrock.authservice.model.response.UserAuthResponse;
import com.flatrock.authservice.service.AuthService;
import com.flatrock.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceFacade {

    private final AuthService authService;
    private final JwtService jwtService;

    public UserAuthResponse login(UserLoginRequest request) {
        UserEntity user = authService.login(request);
        return getAuthResponse(user);
    }

    public UserAuthResponse register(UserRegistrationRequest request) {
        UserEntity user = authService.register(request);
        return getAuthResponse(user);
    }


    private UserAuthResponse getAuthResponse(UserEntity user) {
        String uid = user.getId().toString();
        List<String> roles = user.getRoles().stream().map(d->d.getName().name()).collect(Collectors.toList());
        return UserAuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(uid, roles))
                .build();
    }

}
