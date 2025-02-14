package com.GraduationProject.demo.auth;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean isMale;
    private Boolean smoker;
    private Boolean haveCancer;
    private String type;
    private Boolean haveAFamillyCancer;
    private String familyType ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth ;


}
