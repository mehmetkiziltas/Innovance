package com.innovance.auth;

import com.innovance.auth.token.UserAuthToken;
import com.innovance.auth.token.UserAuthTokenService;
import com.innovance.entity.User;
import com.innovance.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final EmailValidator emailValidator;
    private final UserRepository userRepository;
    private final UserAuthTokenService userAuthTokenService;

    public String login(final Auth auth) {
        if (!emailValidator.isValid(auth.getEmail())) {
            throw new IllegalStateException("Invalid email");
        }
        User user = userRepository.findByEmail(auth.getEmail());
        String token = UUID.randomUUID().toString();
        UserAuthToken authToken = new UserAuthToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                user
        );
        userAuthTokenService.create(authToken);
        return token;
    }

    @Transactional
    public String logout(final UserAuthToken authToken) {
        final Optional<UserAuthToken> token = userAuthTokenService.getToken(authToken.getToken());
        userAuthTokenService.delete(String.valueOf(token.get()));
        return "Logout successful";
    }
}
