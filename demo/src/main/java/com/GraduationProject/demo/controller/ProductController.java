package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.service.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductIngredientService productIngredientService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProductWithIngredients(@RequestBody Map<String, Object> productData) {
        return ResponseEntity.ok(productIngredientService.handleAddingProduct(productData));
    }
}
