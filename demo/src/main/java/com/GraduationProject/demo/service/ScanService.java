package com.GraduationProject.demo.service;

import com.GraduationProject.demo.DTO.ScanResult;
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

    public ScanResult scanProduct(String barcode) {
        System.out.println(barcode);
        Optional<Product> productOpt = productRepository.findByBarcode(barcode);
        if (productOpt.isEmpty()) {
            return new ScanResult("Product not found", null);
        }

        Product product = productOpt.get();
        List<ProductIngredient> productIngredients = productIngredientRepository.findByProduct(product);

        List<HighRiskIngredients> highRiskIngredients = new ArrayList<>();
        for (ProductIngredient productIngredient : productIngredients) {
            Optional<HighRiskIngredients> highRiskIngredientOpt = highRiskRepo.findByIngredientName(productIngredient.getIngredient().getIngredientName());
            highRiskIngredientOpt.ifPresent(highRiskIngredients::add);
        }

        if (highRiskIngredients.isEmpty()) {
            return new ScanResult("No high-risk ingredients found", product);
        } else {
            return new ScanResult("High-risk ingredients found", product, highRiskIngredients);
        }
    }
}