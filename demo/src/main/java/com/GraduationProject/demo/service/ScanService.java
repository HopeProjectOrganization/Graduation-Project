package com.GraduationProject.demo.service;

import com.GraduationProject.demo.DTO.IngredientRiskDTO;
import com.GraduationProject.demo.DTO.ScanResultDTO;
import com.GraduationProject.demo.model.*;
import com.GraduationProject.demo.repo.HighRiskRepo;
import com.GraduationProject.demo.repo.ProductIngredientRepository;
import com.GraduationProject.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScanService {

    private final ProductRepository productRepository;
    private final ProductIngredientRepository productIngredientRepository;
    private final HighRiskRepo highRiskRepo;

    public ScanResultDTO scanProduct(String barcode) {
        System.out.println("Scanning product with barcode: " + barcode);

        Optional<Product> productOpt = productRepository.findByBarcode(barcode);
        if (productOpt.isEmpty()) {
            return new ScanResultDTO("Product not found", null, null);
        }

        Product product = productOpt.get();
        List<ProductIngredient> productIngredients = productIngredientRepository.findByProduct(product);

        List<IngredientRiskDTO> highRiskDTOs = new ArrayList<>();
        for (ProductIngredient productIngredient : productIngredients) {
            Optional<HighRiskIngredients> highRiskIngredientOpt = highRiskRepo.findByIngredientName(productIngredient.getIngredient().getIngredientName());
            if (highRiskIngredientOpt.isPresent()) {
                HighRiskIngredients highRiskIngredient = highRiskIngredientOpt.get();

                Double ingredientPercentage = extractPercentage(productIngredient.getPercentage());
                String safeLimit = highRiskIngredient.getSafeLimit();

                String riskCategory = "Unknown Risk";
                if (ingredientPercentage != null && safeLimit != null && !safeLimit.equals("0% موصى به")) {
                    if (isAboveSafeLimit(ingredientPercentage, safeLimit)) {
                        riskCategory = categorizeRiskLevel(ingredientPercentage, safeLimit);
                    } else {
                        riskCategory = "E (Minimal or No Risk)";
                    }
                } else {
                    // If ingredientPercentage is null, categorize by riskLevel
                    riskCategory = categorizeByRiskLevel(highRiskIngredient.getRiskLevel());
                }

                IngredientRiskDTO dto = IngredientRiskDTO.builder()
                        .ingredientName(highRiskIngredient.getIngredientName())
                        .riskLevel(highRiskIngredient.getRiskLevel())
                        .safeLimit(highRiskIngredient.getSafeLimit())
                        .englishDescription(highRiskIngredient.getEnglish_description())
                        .arabicDescription(highRiskIngredient.getArabic_description())
                        .riskCategory(riskCategory)
                        .build();

                highRiskDTOs.add(dto);
            }
        }

        if (highRiskDTOs.isEmpty()) {
            return new ScanResultDTO("No high-risk ingredients found", product, null);
        } else {
            return new ScanResultDTO("High-risk ingredients found", product, highRiskDTOs);
        }
    }

    private Double extractPercentage(String value) {
        try {
            if (value == null) return null;
            String numeric = value.replaceAll("[^\\d.]", "");
            return Double.parseDouble(numeric);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isAboveSafeLimit(Double percentage, String safeLimit) {
        try {
            Double safeLimitValue = Double.parseDouble(safeLimit.replaceAll("[^\\d.]", ""));
            return percentage > safeLimitValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String categorizeRiskLevel(Double ingredientPercentage, String safeLimit) {
        try {
            Double safeLimitValue = Double.parseDouble(safeLimit.replaceAll("[^\\d.]", ""));
            double percentageDifference = (ingredientPercentage / safeLimitValue) * 100;

            if (percentageDifference >= 150) {
                return "A (High Risk)";
            } else if (percentageDifference >= 125) {
                return "B (Moderate Risk)";
            } else if (percentageDifference >= 110) {
                return "C (Medium Risk)";
            } else if (percentageDifference >= 100) {
                return "D (Low Risk)";
            } else {
                return "E (Minimal or No Risk)";
            }
        } catch (NumberFormatException e) {
            return "Unknown Risk";
        }
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    // New method to categorize by riskLevel when ingredientPercentage is null
    private String categorizeByRiskLevel(String riskLevel) {
        if ("HIGH".equalsIgnoreCase(riskLevel)) {
            return "A (High Risk)";
        } else if ("MEDIUM".equalsIgnoreCase(riskLevel)) {
            return "B (Moderate Risk)";
        } else if ("LOW".equalsIgnoreCase(riskLevel)) {
            return "C (Low Risk)";
        } else {
            return "Unknown Risk";
        }
    }
}
