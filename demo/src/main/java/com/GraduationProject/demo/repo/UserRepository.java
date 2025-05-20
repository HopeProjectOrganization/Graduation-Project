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
    @Query("UPDATE User u SET u.resetPasswordCode = ?1, u.resetPasswordCodeExpiry = ?2 WHERE u.email = ?3")
    void updateResetPasswordCode(String code, LocalDateTime expiry, String email);
    boolean existsByEmail(String email);

    Optional<User>findByResetPasswordCode(String code);

}
