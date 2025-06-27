package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.Recipe;
import com.GraduationProject.demo.repo.RecipeRepository;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public Recipe save(Recipe recipe) {
        if (recipe.getRecipeId() == null || recipe.getRecipeId().isEmpty()) {
            recipe.setRecipeId(UUID.randomUUID().toString());
        }
        return repository.save(recipe);
    }

    public Optional<Recipe> getByRecipeId(String recipeId) {
        return repository.findByRecipeId(recipeId);
    }

    public List<Recipe> getAll() {
        return repository.findAll();
    }

    public void deleteByRecipeId(String recipeId) {
        repository.findByRecipeId(recipeId).ifPresent(repository::delete);
    }

    // ✏️ New update method
    public Recipe update(Integer recipeId, Recipe newRecipe) {
        return repository.findById(recipeId)
                .map(existing -> {
                    existing.setName(newRecipe.getName());
                    existing.setCategory(newRecipe.getCategory());
                    existing.setArea(newRecipe.getArea());
                    existing.setInstructions(newRecipe.getInstructions());
                    existing.setImageUrl(newRecipe.getImageUrl());
                    existing.setTags(newRecipe.getTags());
                    existing.setYoutubeUrl(newRecipe.getYoutubeUrl());
                    existing.setIngredients(newRecipe.getIngredients());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Recipe not found: " + recipeId));
    }
}
