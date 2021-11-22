package com.innovance.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private String username;
    private String email;
    private String identityNumber;
    private String password;

}
