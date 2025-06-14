package com.GraduationProject.demo.DTO;

import lombok.Data;

@Data
public class SaveFavoriteMealRequest {
    private String mealId;
    private String category;
    private String type;
}
