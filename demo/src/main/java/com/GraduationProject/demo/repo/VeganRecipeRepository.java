package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.VeganRecipeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeganRecipeRepository extends JpaRepository<VeganRecipeDetail, Integer> {
    Optional<VeganRecipeDetail> findByVeganId(String veganId);

}
