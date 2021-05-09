package com.oob.domain.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;

    private String refreshToken;

    private String tokenType;
}
