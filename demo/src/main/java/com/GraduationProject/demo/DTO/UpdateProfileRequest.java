package com.GraduationProject.demo.DTO;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String oldPassword;
    private String newPassword;
    private String imageId;

    // Optional: Include other profile fields you want updated
    private Boolean isMale;
    private Boolean smoker;
    private Boolean haveCancer;
    private Boolean haveAFamillyCancer;
    private String familyType;
    private String type;
}