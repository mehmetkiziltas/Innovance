package com.innovance.entity.vm;

import com.innovance.entity.Account;
import com.innovance.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserVM {
    private Long id;
    private String username;
    private String identityNumber;
    private String email;
    private List<Account> accounts;

    public UserVM(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setIdentityNumber(user.getIdentityNumber());
        this.setEmail(user.getEmail());
        this.setAccounts(user.getAccount());
    }
}
