package com.oob.controller;

import com.oob.domain.payload.request.SignInRequest;
import com.oob.domain.payload.response.TokenResponse;
import com.oob.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PutMapping
    public TokenResponse refreshToken(@RequestHeader("X-Refresh-Token") @Valid String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

}
