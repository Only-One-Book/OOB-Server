package com.oob.service.auth;

import com.oob.domain.entity.User;
import com.oob.domain.payload.request.SignInRequest;
import com.oob.domain.payload.response.TokenResponse;
import com.oob.domain.repository.UserRepository;
import com.oob.exception.InvalidTokenException;
import com.oob.exception.UserNotFoundException;
import com.oob.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    @Override
    public TokenResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .filter(u -> passwordEncoder.matches(signInRequest.getPassword(), u.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        return responseToken(user.getEmail());
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        if(!jwtTokenProvider.isRefreshToken(refreshToken)) throw new InvalidTokenException();
        String email = jwtTokenProvider.getUserEmail(refreshToken);
        return responseToken(email);
    }

    private TokenResponse responseToken(String email) {
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(email))
                .refreshToken(jwtTokenProvider.generateRefreshToken(email))
                .tokenType(prefix)
                .build();
    }
}
