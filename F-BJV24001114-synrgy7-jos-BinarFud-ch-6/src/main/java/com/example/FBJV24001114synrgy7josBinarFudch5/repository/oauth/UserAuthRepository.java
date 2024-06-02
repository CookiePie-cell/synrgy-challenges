package com.example.FBJV24001114synrgy7josBinarFudch5.repository.oauth;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.oauth.AuthUser;
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
