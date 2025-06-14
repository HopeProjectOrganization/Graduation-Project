package com.GraduationProject.demo.controller;


import com.GraduationProject.demo.DTO.SaveFavoriteMealRequest;
import com.GraduationProject.demo.model.UserFavoriteMeal;
import com.GraduationProject.demo.service.FavoriteMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-meals")
@RequiredArgsConstructor
public class FavoriteMealController {

    private final FavoriteMealService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SaveFavoriteMealRequest request) {
        service.saveFavoriteMeal(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.getByCategory(category));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type) {
        return ResponseEntity.ok(service.getByType(type));
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<?> getById(@PathVariable String mealId) {
        return ResponseEntity.ok(service.getByMealId(mealId));
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<?> deleteById(@PathVariable String mealId) {
        service.deleteById(mealId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
