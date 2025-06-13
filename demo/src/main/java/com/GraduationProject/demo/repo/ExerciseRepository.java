package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.Exercise;
import com.GraduationProject.demo.model.VeganRecipeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findByExcersiesId(String excersiesId);

}
