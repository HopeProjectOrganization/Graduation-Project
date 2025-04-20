package com.GraduationProject.demo.DTO;

import com.GraduationProject.demo.model.HighRiskIngredients;
import com.GraduationProject.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScanResultDTO {
    private String message;
    private Product product;
    private List<IngredientRiskDTO> highRiskIngredients;
}
