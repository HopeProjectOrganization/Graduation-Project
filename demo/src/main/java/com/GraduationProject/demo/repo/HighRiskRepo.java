package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.HighRiskIngredients;
import com.GraduationProject.demo.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HighRiskRepo extends JpaRepository<HighRiskIngredients, Integer> {
    Optional<HighRiskIngredients> findByIngredientName(String ingredientName);
}