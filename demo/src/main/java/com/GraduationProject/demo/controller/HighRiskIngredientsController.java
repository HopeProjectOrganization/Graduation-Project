package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.HighRiskIngredients;
import com.GraduationProject.demo.service.HighRiskIngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/high-risk-ingredients")
@RequiredArgsConstructor
public class HighRiskIngredientsController {

    private final HighRiskIngredientsService service;

    @PostMapping
    public ResponseEntity<HighRiskIngredients> addIngredient(@RequestBody HighRiskIngredients ingredient) {
        return ResponseEntity.ok(service.addIngredient(ingredient));
    }

    @GetMapping
    public ResponseEntity<List<HighRiskIngredients>> getAllIngredients() {
        return ResponseEntity.ok(service.getAllIngredients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer id) {
        service.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HighRiskIngredients> updateIngredient(
            @PathVariable Integer id,
            @RequestBody HighRiskIngredients updatedIngredient) {
        return ResponseEntity.ok(service.updateIngredient(id, updatedIngredient));
    }
}
