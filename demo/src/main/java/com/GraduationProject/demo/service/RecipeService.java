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
}

