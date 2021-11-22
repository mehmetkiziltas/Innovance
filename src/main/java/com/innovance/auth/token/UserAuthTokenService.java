package com.innovance.auth.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAuthTokenService {

    private final UserAuthTokenRepository userAuthTokenRepository;

    public UserAuthToken create(UserAuthToken token) {
        return userAuthTokenRepository.save(token);
    }

    public Optional<UserAuthToken> getToken(final String token) {
        return userAuthTokenRepository.findByToken(token);
    }

    public void delete(final String token) {
        userAuthTokenRepository.deleteByToken(token);
    }
}
