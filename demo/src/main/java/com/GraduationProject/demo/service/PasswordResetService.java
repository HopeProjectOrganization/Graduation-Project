package com.GraduationProject.demo.service;

import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder
    private String userEmailToResend="";
    private String userCode="" ;


    public void generateResetCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userEmailToResend=email;
        String code = generateFourDigitNumber();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(1); // Token valid for 1 hour

        userRepository.updateResetPasswordCode(code, expiry, email);

        String resetLink = "Your Verification Code : " + code;
        emailService.sendEmail(email, "Verify yor email address" + resetLink);
    }


    public void verifyCode(String code) {
        User user = userRepository.findByResetPasswordCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid Code"));

        if (user.getResetPasswordCodeExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Code expired");
        }

        userCode=code;
        user.setVerified(true);
        userRepository.save(user);
    }

    public void resendCode(){
        User user = userRepository.findByEmail(userEmailToResend)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String code = generateFourDigitNumber();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(10);

        userRepository.updateResetPasswordCode(code, expiry,  userEmailToResend);

        String resetLink = "Your Verification Code : " + code;
        emailService.sendEmail( userEmailToResend, "Verify yor email address" + resetLink);
    }


    public void resetPassword(String newPasswordConfirm, String newPassword) {

        User user = userRepository.findByEmail(userEmailToResend)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getResetPasswordCodeExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Code expired");
        }

        if (!user.getVerified()) {
            throw new RuntimeException("User not verified");
        }
        if (!Objects.equals(newPasswordConfirm, newPassword)) {
            throw new RuntimeException("Password not the same");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordCode(null);
        user.setResetPasswordCodeExpiry(null);
        user.setVerified(false);
        userEmailToResend=null;

        userRepository.save(user);
    }
    public static String generateFourDigitNumber() {
        Random random = new Random();
        int code= 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }

}