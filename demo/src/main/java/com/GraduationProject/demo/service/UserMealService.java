package com.GraduationProject.demo.service;



import com.GraduationProject.demo.DTO.MealCategory;
import com.GraduationProject.demo.DTO.MealCategoryResponse;
import com.GraduationProject.demo.DTO.SaveMealRequest;
import com.GraduationProject.demo.model.UserMeal;
import com.GraduationProject.demo.repo.UserMealRepository;
import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserMealService {

    private final UserRepository userRepository;
    private final UserMealRepository userMealRepository;

    public void saveMeals(SaveMealRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MealCategory category = MealCategory.valueOf(request.getCategory().toUpperCase());
        LocalDate date = request.getDateTime().toLocalDate();

        for (String mealId : request.getMealIds()) {
            UserMeal meal = UserMeal.builder()
                    .user(user)
                    .mealDate(date)
                    .category(category)
                    .mealId(mealId)
                    .build();
            userMealRepository.save(meal);
        }
    }

    public MealCategoryResponse getMeals(Integer userId, String dateStr, String categoryStr) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate date = LocalDate.parse(dateStr);
        MealCategory category = MealCategory.valueOf(categoryStr.toUpperCase());

        List<String> mealIds = userMealRepository
                .findByUserAndMealDateAndCategory(user, date, category)
                .stream()
                .map(UserMeal::getMealId)
                .toList();

        return new MealCategoryResponse(categoryStr, date, mealIds);
    }



}
