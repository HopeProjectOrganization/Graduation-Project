package com.GraduationProject.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientRiskDTO {
    private String ingredientName;
    private String riskLevel;
    private String safeLimit;
    private String englishDescription;
    private String arabicDescription;
    private String riskCategory;
}
