package com.oob.service.user;

import com.oob.domain.payload.request.SignUpRequest;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
}