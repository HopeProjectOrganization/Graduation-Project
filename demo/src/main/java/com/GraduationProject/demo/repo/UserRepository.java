package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.resetPasswordToken = ?1, u.resetPasswordTokenExpiry = ?2 WHERE u.email = ?3")
    void updateResetPasswordToken(String token, LocalDateTime expiry, String email);

    Optional<User> findByResetPasswordToken(String token);
}
