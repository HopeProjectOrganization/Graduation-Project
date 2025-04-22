package com.GraduationProject.demo.service;

import com.GraduationProject.demo.DTO.UpdateProfileRequest;
import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public User getProfile(String email) {
        User user =userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setUsername(user.getName());
        return user;
    }



    public User updateProfile(String currentEmail, UpdateProfileRequest request) {
        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // lw he8er el email el email el gded mwgod fe el data base 2bl kda wla eh ?
        if (request.getEmail() != null && !request.getEmail().equals(currentEmail)) {
            Optional<User> existing = userRepository.findByEmail(request.getEmail());
            if (existing.isPresent()) {
                throw new IllegalArgumentException("Email is already in use.");
            }
            user.setEmail(request.getEmail());
        }
        if (request.getName() != null ) user.setName(request.getName());
        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getPhone() != null) user.setPhone(request.getPhone());

        // Update password if requested
        if (request.getOldPassword() != null && request.getNewPassword() != null) {
            if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Old password is incorrect.");
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        if (request.getImageId() != null) user.setImageId(request.getImageId());
        if (request.getIsMale() != null) user.setIsMale(request.getIsMale());
        if (request.getSmoker() != null) user.setSmoker(request.getSmoker());
        if (request.getHaveCancer() != null) user.setHaveCancer(request.getHaveCancer());
        if (request.getHaveAFamillyCancer() != null) user.setHaveAFamillyCancer(request.getHaveAFamillyCancer());
        if (request.getFamilyType() != null) user.setFamilyType(request.getFamilyType());
        if (request.getType() != null) user.setType(request.getType());

        return userRepository.save(user);
    }


//    public User updateProfile(String email, User updatedData) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        user.setUsername(updatedData.getUsername());
//        user.setPhone(updatedData.getPhone());
//        user.setDateOfBirth(updatedData.getDateOfBirth());
//        user.setIsMale(updatedData.getIsMale());
//        user.setSmoker(updatedData.getSmoker());
//        user.setHaveCancer(updatedData.getHaveCancer());
//        user.setHaveAFamillyCancer(updatedData.getHaveAFamillyCancer());
//        user.setFamilyType(updatedData.getFamilyType());
//        user.setType(updatedData.getType());
//
//        return userRepository.save(user);
//    }

}
