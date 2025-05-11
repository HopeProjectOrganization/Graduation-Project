package com.GraduationProject.demo.controller;


import com.GraduationProject.demo.DTO.UpdateProfileRequest;
import com.GraduationProject.demo.service.UserServices;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userService;

    @GetMapping
    public ResponseEntity<User> getProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(userService.getProfile(email));
    }


    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request,
                                           Authentication authentication) {
        String email = authentication.getName();
        try {
            User updatedUser = userService.updateProfile(email, request);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteMyAccount(Authentication authentication) {
        String email = authentication.getName();
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("Your account has been deleted successfully.");
    }



//    @PutMapping
//    public ResponseEntity<User> updateProfile(@RequestBody User updatedUser, Authentication authentication) {
//        String email = authentication.getName();
//        return ResponseEntity.ok(userService.updateProfile(email, updatedUser));
//    }
}
