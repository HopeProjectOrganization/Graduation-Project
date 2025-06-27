package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
//    Optional<Recipe> findByRecipeId(Integer recipeId);
    Optional<Recipe> findByRecipeId(String recipeId);

}
