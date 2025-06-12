package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, String> {
}