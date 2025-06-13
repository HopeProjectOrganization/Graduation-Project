package com.GraduationProject.demo.repo;



import com.GraduationProject.demo.model.FavoriteMeal;
import com.GraduationProject.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteMealRepository extends JpaRepository<FavoriteMeal, String> {

    List<FavoriteMeal> findByUser(User user);

    List<FavoriteMeal> findByUserAndCategory(User user, String category);

    List<FavoriteMeal> findByUserAndType(User user, String type);

    Optional<FavoriteMeal> findByUserAndId(User user, String id);

    void deleteByUserAndId(User user, String id);

    void deleteAllByUser(User user);
}

