package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.News;
import com.GraduationProject.demo.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByCategory(NewsCategory category);
}
