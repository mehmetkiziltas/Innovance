package com.innovance.service;

import com.innovance.entity.User;
import com.innovance.entity.vm.UpdateUserVM;
import com.innovance.entity.vm.UserVM;
import com.innovance.repository.AccountRepository;
import com.innovance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public List<UserVM> getAllUsers() {
        final List<User> inDB = userRepository.findAll();
        return inDB.stream().map(UserVM::new).collect(Collectors.toList());
    }

    public User updateUser(final String identityNumber, final UpdateUserVM updateUserVM) {
        final User inDB = userRepository.getByIdentityNumber(identityNumber);
        inDB.setUsername(updateUserVM.getUsername());
        inDB.setPassword(passwordEncoder.encode(updateUserVM.getPassword()));
        inDB.setEmail(updateUserVM.getEmail());
        return userRepository.save(inDB);
    }

    public void deleteUser(final String identityNumber) {
        final User inDB = userRepository.getByIdentityNumber(identityNumber);
        accountRepository.deleteByUser(inDB);
        userRepository.delete(inDB);
    }

    public User login(final String identityNumber, final String password) {
        final User inDB = userRepository.getByIdentityNumber(identityNumber);
        if (inDB == null) {
            return null;
        }
        if (passwordEncoder.matches(password, inDB.getPassword())) {
            return inDB;
        }
        return null;
    }
}
