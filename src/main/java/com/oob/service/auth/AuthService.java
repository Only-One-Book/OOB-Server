package com.oob.service.auth;

import com.oob.domain.payload.request.SignInRequest;
import com.oob.domain.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(SignInRequest signInRequest);
    TokenResponse refreshToken(String refreshToken);
}
