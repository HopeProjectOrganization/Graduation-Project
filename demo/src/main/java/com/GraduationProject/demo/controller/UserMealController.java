package com.GraduationProject.demo.controller;



import com.GraduationProject.demo.DTO.MealCategoryResponse;
import com.GraduationProject.demo.DTO.SaveMealRequest;
import com.GraduationProject.demo.service.UserMealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-meals")
@RequiredArgsConstructor
public class UserMealController {

    private final UserMealService userMealService;

    @PostMapping
    public void saveMeals(@RequestBody SaveMealRequest request) {
        userMealService.saveMeals(request);
    }

    @GetMapping
    public MealCategoryResponse getMealsByDateAndCategory(
            @RequestParam Integer userId,
            @RequestParam String date,
            @RequestParam String category
    ) {
        return userMealService.getMeals(userId, date, category);
    }




}
