package com.GraduationProject.demo.service;



import com.GraduationProject.demo.model.Meal;
import com.GraduationProject.demo.repo.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Optional<Meal> getMealById(String id) {
        return mealRepository.findById(id);
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal updateMeal(String id, Meal updatedMeal) {
        return mealRepository.findById(id).map(existingMeal -> {
            existingMeal.setName(updatedMeal.getName());
            existingMeal.setDescription(updatedMeal.getDescription());
            existingMeal.setImage(updatedMeal.getImage());
            existingMeal.setPrepareTime(updatedMeal.getPrepareTime());
            existingMeal.setCookTime(updatedMeal.getCookTime());
            existingMeal.setServings(updatedMeal.getServings());
            existingMeal.setTags(updatedMeal.getTags());
            existingMeal.setSteps(updatedMeal.getSteps());
            existingMeal.setIngredients(updatedMeal.getIngredients());
            existingMeal.setNutrients(updatedMeal.getNutrients());
            return mealRepository.save(existingMeal);
        }).orElse(null);
    }

    public boolean deleteMeal(String id) {
        Optional<Meal> meal = mealRepository.findById(id);
        if (meal.isPresent()) {
            mealRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
