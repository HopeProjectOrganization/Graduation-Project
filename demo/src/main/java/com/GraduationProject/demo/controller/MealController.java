package com.GraduationProject.demo.controller;



import com.GraduationProject.demo.model.Meal;
import com.GraduationProject.demo.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping
    public ResponseEntity<Meal> saveMeal(@RequestBody Meal meal) {
        return ResponseEntity.ok(mealService.saveMeal(meal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable String id) {
        return mealService.getMealById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable String id, @RequestBody Meal updatedMeal) {
        Meal updated = mealService.updateMeal(id, updatedMeal);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable String id) {
        boolean deleted = mealService.deleteMeal(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}

