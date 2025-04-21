package com.GraduationProject.demo.service;

import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;

    public User getProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User updateProfile(String email, User updatedData) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setUsername(updatedData.getUsername());
        user.setPhone(updatedData.getPhone());
        user.setDateOfBirth(updatedData.getDateOfBirth());
        user.setIsMale(updatedData.getIsMale());
        user.setSmoker(updatedData.getSmoker());
        user.setHaveCancer(updatedData.getHaveCancer());
        user.setHaveAFamillyCancer(updatedData.getHaveAFamillyCancer());
        user.setFamilyType(updatedData.getFamilyType());
        user.setType(updatedData.getType());

        return userRepository.save(user);
    }

}
