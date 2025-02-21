package com.GraduationProject.demo.service;

import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    public void generateResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String code = generateFourDigitNumber();
        LocalDateTime expiry = LocalDateTime.now().plusHours(1); // Token valid for 1 hour

        userRepository.updateResetPasswordCode(code, expiry, email);

        String resetLink = "Your Verification Code : " + code;
        emailService.sendEmail(email, "Verify yor email address" + resetLink);
    }

    public void resetPassword(String code, String newPassword) {
        User user = userRepository.findByResetPasswordCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid Code"));

        if (user.getResetPasswordCodeExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Code expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordCode(null);
        user.setResetPasswordCodeExpiry(null);
        userRepository.save(user);
    }
    public static String generateFourDigitNumber() {
        Random random = new Random();
        int code= 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }

}