package com.innovance.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserAuthTokenRepository extends JpaRepository<UserAuthToken, Long> {
    Optional<UserAuthToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE UserAuthToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);

    void deleteByToken(String token);
}
