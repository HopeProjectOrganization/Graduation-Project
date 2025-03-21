package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.News;
import com.GraduationProject.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public News addNews(@RequestBody News news) {
        if (news.getCategory() == null) {
            throw new RuntimeException("Category cannot be null");
        }
        return newsService.addNews(news.getTitle(), news.getContent(), news.getCategory().name(), news.getImageUrl());
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<News>> getNewsByCategory(@PathVariable String category) {
        List<News> newsArticles = newsService.getNewsByCategory(category);
        return ResponseEntity.ok(newsArticles);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        try {
            newsService.deleteNewsById(id);
            return ResponseEntity.ok("News with id " + id +" deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News news) {
        if (news.getCategory() == null) {
            throw new RuntimeException("Category cannot be null");
        }
        News updatedNews = newsService.updateNews(id, news.getTitle(), news.getContent(), news.getCategory().name(), news.getImageUrl());
        return ResponseEntity.ok(updatedNews);
    }

}
