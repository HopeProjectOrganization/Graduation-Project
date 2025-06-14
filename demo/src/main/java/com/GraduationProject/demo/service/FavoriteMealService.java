package com.GraduationProject.demo.service;


import com.GraduationProject.demo.DTO.SaveFavoriteMealRequest;
import com.GraduationProject.demo.model.UserFavoriteMeal;

import com.GraduationProject.demo.model.UserFavoriteMeal;
import com.GraduationProject.demo.repo.FavoriteMealRepository;
import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteMealService {

    private final FavoriteMealRepository favoriteMealRepository;
    private final UserRepository userRepository;

    public void saveFavoriteMeal(SaveFavoriteMealRequest request) {
        User user = getCurrentUser();

        if (favoriteMealRepository.existsByUserAndMealId(user, request.getMealId())) {
            return;
        }

        UserFavoriteMeal favorite = UserFavoriteMeal.builder()
                .mealId(request.getMealId())
                .category(request.getCategory())
                .type(request.getType())
                .user(user)
                .build();

        favoriteMealRepository.save(favorite);
    }

    public List<UserFavoriteMeal> getByCategory(String category) {
        return favoriteMealRepository.findByUserAndCategory(getCurrentUser(), category);
    }

    public List<UserFavoriteMeal> getByType(String type) {
        return favoriteMealRepository.findByUserAndType(getCurrentUser(), type);
    }

    public UserFavoriteMeal getByMealId(String id) {
        return favoriteMealRepository.findByUserAndMealId(getCurrentUser(), id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Transactional
    public void deleteById(String id) {
        favoriteMealRepository.deleteByUserAndMealId(getCurrentUser(), id);
    }

    @Transactional
    public void deleteAll() {
        favoriteMealRepository.deleteAllByUser(getCurrentUser());
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // from JWT
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
