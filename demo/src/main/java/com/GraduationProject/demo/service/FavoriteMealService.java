package com.GraduationProject.demo.service;



import com.GraduationProject.demo.model.FavoriteMeal;
import com.GraduationProject.demo.repo.FavoriteMealRepository;
import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;
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

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addFavoriteMeal(FavoriteMeal meal) {
        meal.setUser(getCurrentUser());
        favoriteMealRepository.save(meal);
    }

    public List<FavoriteMeal> getFavoritesByCategory(String category) {
        return favoriteMealRepository.findByUserAndCategory(getCurrentUser(), category);
    }

    public List<FavoriteMeal> getFavoritesByType(String type) {
        return favoriteMealRepository.findByUserAndType(getCurrentUser(), type);
    }

    public FavoriteMeal getFavoriteById(String id) {
        return favoriteMealRepository.findByUserAndId(getCurrentUser(), id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    public void deleteById(String id) {
        favoriteMealRepository.deleteByUserAndId(getCurrentUser(), id);
    }

    public void deleteAll() {
        favoriteMealRepository.deleteAllByUser(getCurrentUser());
    }

    public List<FavoriteMeal> getAllFavorites() {
        return favoriteMealRepository.findByUser(getCurrentUser());
    }
}
