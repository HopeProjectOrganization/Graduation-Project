package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.DTO.MealCategory;
import com.GraduationProject.demo.model.UserMeal;
import com.GraduationProject.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserMealRepository extends JpaRepository<UserMeal, Long> {
    List<UserMeal> findByUserAndMealDateAndCategory(User user, LocalDate date, MealCategory category);
}