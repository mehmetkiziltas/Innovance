package com.innovance.repository;

import com.innovance.entity.Account;
import com.innovance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    void deleteByUser(User user);
    List<Account> findByUser(User user);
    Account findByUserAndId(User user, Long id);
    void deleteByUserAndId(User user, Long id);

}
