package com.GraduationProject.demo.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaveMealRequest {
    private Integer userId;
    private LocalDateTime dateTime;
    private String category;
    private List<String> mealIds;
}