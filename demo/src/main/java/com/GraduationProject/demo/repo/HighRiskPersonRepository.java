package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.HighRiskPerson;
import com.GraduationProject.demo.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HighRiskPersonRepository extends JpaRepository<HighRiskPerson, Long> {
    List<HighRiskPerson> findByCategory(NewsCategory category);
}
