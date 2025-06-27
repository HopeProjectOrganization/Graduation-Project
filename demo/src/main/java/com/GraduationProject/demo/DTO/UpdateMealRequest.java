package com.GraduationProject.demo.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateMealRequest {
    private Integer userId;
    private String date;
    private String category;
    private String oldMealId;
    private String newMealId;
}