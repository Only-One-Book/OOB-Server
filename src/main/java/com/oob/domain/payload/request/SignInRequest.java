package com.oob.domain.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignInRequest {
    @Email
    private String email;

    @NotBlank
    private String password;
}
