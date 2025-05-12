package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.HereditaryPeople;
import com.GraduationProject.demo.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HereditaryPeopleRepository extends JpaRepository<HereditaryPeople, Long> {
    List<HereditaryPeople> findByCategory(NewsCategory category);
}

