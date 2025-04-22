package com.GraduationProject.demo.service;


import com.GraduationProject.demo.auth.AuthenticationResponse;
import com.GraduationProject.demo.auth.RegisterRequest;
import com.GraduationProject.demo.auth.AuthenticationRequest;
import com.GraduationProject.demo.config.JwtService;
import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.Role;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return AuthenticationResponse.builder()
                    .token("Email already exists.")
                    .build();
        }

        var user = User.builder()
                .haveCancer(request.getHaveCancer())
                .type(request.getType())
                .familyType(request.getFamilyType())
                .haveAFamillyCancer(request.getHaveAFamillyCancer())
                .phone(request.getPhone())
                .smoker(request.getSmoker())
                .isMale(request.getIsMale())
                .dateOfBirth(request.getDateOfBirth())
                .username(request.getUsername())
                .imageId(request.getImageId())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
