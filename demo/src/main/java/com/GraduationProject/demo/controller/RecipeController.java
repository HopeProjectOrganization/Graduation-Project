package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.Recipe;
import com.GraduationProject.demo.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Recipe> create(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(service.save(recipe));
    }

    @GetMapping
    public List<Recipe> getAll() {
        return service.getAll();
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<Recipe> getByRecipeId(@PathVariable String recipeId) {
        return service.getByRecipeId(recipeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> delete(@PathVariable String recipeId) {
        service.deleteByRecipeId(recipeId);
        return ResponseEntity.noContent().build();
    }
}

