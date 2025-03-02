package com.GraduationProject.demo.DTO;

import com.GraduationProject.demo.model.HighRiskIngredients;
import com.GraduationProject.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScanResult {
    private String message;
    private Product product;
    private List<HighRiskIngredients> highRiskIngredients;

    public ScanResult(String message, Product product) {
        this.message = message;
        this.product = product;
    }
}