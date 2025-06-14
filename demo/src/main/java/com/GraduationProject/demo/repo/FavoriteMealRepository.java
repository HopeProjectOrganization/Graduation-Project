package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.UserFavoriteMeal;
import com.GraduationProject.demo.model.UserFavoriteMeal;
import com.GraduationProject.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteMealRepository extends JpaRepository<UserFavoriteMeal, Long> {

    boolean existsByUserAndMealId(User user, String mealId);

    List<UserFavoriteMeal> findByUserAndCategory(User user, String category);
    List<UserFavoriteMeal> findByUserAndType(User user, String type);
    Optional<UserFavoriteMeal> findByUserAndMealId(User user, String mealId);

    void deleteByUserAndMealId(User user, String mealId);
    void deleteAllByUser(User user);
}
