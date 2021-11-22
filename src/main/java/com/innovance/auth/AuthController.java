package com.innovance.auth;

import com.innovance.auth.token.UserAuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public String login(@RequestBody Auth auth) {
        return authService.login(auth);
    }

    @PostMapping("/logout")
    public String logout(@RequestBody UserAuthToken userAuthToken) {
        return authService.logout(userAuthToken);
    }
}
