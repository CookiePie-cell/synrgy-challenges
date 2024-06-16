package com.challenge7.auth.repository;


import com.challenge7.auth.model.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<AuthUser, Long> {
    @Query("FROM AuthUser u WHERE LOWER(u.username) = LOWER(?1)")
    Optional<AuthUser> findOneByUsername(String username);

    @Query("FROM AuthUser u WHERE u.otp = ?1")
    AuthUser findOneByOTP(String otp);

    @Query("FROM AuthUser u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<AuthUser> checkExistingEmail(String username);
}
