package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.model.ProductIngredient;
import com.GraduationProject.demo.service.ProductIngredientService;
import com.GraduationProject.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductIngredientService productIngredientService;
    private final ProductService productService;
    @PostMapping("/add")
    public ResponseEntity<Product> addProductWithIngredients(@RequestBody Map<String, Object> productData) {
        return ResponseEntity.ok(productIngredientService.handleAddingProduct(productData));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductIngredient>> getAllProductIngredients() {
        List<ProductIngredient> all = productIngredientService.getAllProductIngredients();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductIngredient> updateProductIngredient(
            @PathVariable Integer id,
            @RequestBody Map<String, String> data
    ) {
        String newIngredientName = data.get("ingredientName");
        String newPercentage = data.get("percentage");

        ProductIngredient updated = productIngredientService.updateProductIngredient(id, newIngredientName, newPercentage);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @PutMapping("/full-update/{id}")
    public ResponseEntity<ProductIngredient> updateFullProductIngredient(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> data
    ) {
        ProductIngredient updated = productIngredientService.updateFullProductIngredient(id, data);
        return ResponseEntity.ok(updated);
    }

}
