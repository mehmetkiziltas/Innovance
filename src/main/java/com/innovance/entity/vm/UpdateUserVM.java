package com.innovance.entity.vm;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUserVM {

    @NotNull
    @Size(min = 4,max = 255)
    @Column(name = "username")
    private String username;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{Your password does not meet the password requirements.}")
    @Size(min = 8,max = 255)
    @Column(name = "password")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$", message = "{Your email does not meet the email requirements.}")
    @Column(name = "email")
    //@UniqueEmail
    private String email;
}