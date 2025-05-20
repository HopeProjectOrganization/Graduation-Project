package com.GraduationProject.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class MealCategoryResponse {
    private String category;
    private LocalDate date;
    private List<String> mealIds;
}