package com.oob.service.user;

import com.oob.domain.entity.User;
import com.oob.domain.payload.request.SignUpRequest;
import com.oob.domain.repository.UserRepository;
import com.oob.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        if(repository.findByEmail(signUpRequest.getEmail()).isPresent()) throw new UserAlreadyExistsException();

        String email = signUpRequest.getEmail();
        String name = signUpRequest.getName();
        String password = passwordEncoder.encode(signUpRequest.getPassword());

        repository.save(
                User.builder()
                .email(email)
                        .name(name)
                .password(password)
                .build()
        );
    }
}
