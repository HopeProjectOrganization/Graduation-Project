package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.DietCategory;
import com.GraduationProject.demo.model.HealthyDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthyDietRepository extends JpaRepository<HealthyDiet, Long> {
    List<HealthyDiet> findByCategory(DietCategory category);
}
