package com.GraduationProject.demo.controller;



import com.GraduationProject.demo.model.FavoriteMeal;
import com.GraduationProject.demo.service.FavoriteMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoriteMeal")
@RequiredArgsConstructor
public class FavoriteMealController {

    private final FavoriteMealService favoriteMealService;

    @PostMapping
    public ResponseEntity<Void> addFavorite(@RequestBody FavoriteMeal meal) {
        favoriteMealService.addFavoriteMeal(meal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<FavoriteMeal>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(favoriteMealService.getFavoritesByCategory(category));
    }

    @GetMapping("/type")
    public ResponseEntity<List<FavoriteMeal>> getByType(@RequestParam String type) {
        return ResponseEntity.ok(favoriteMealService.getFavoritesByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteMeal> getById(@PathVariable String id) {
        return ResponseEntity.ok(favoriteMealService.getFavoriteById(id));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteMeal>> getAll() {
        return ResponseEntity.ok(favoriteMealService.getAllFavorites());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        favoriteMealService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        favoriteMealService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
