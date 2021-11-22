package com.innovance.repository;

import com.innovance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByIdentityNumber(String identityNumber);
    User getByIdentityNumber(String identityNumber);

    User findByUsername(String username);
}