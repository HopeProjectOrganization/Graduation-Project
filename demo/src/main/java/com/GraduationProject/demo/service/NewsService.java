package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.Exercise;
import com.GraduationProject.demo.model.News;
import com.GraduationProject.demo.model.NewsCategory;
import com.GraduationProject.demo.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

//    public News addNews(String title, String content, String category, String imageUrl) {
//        try {
//            NewsCategory newsCategory = NewsCategory.valueOf(category.toUpperCase());
//            News news = News.builder()
//                    .title(title)
//                    .content(content)
//                    .category(newsCategory)
//                    .imageUrl(imageUrl)
//                    .build();
//            return newsRepository.save(news);
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("Invalid category: " + category);
//        }
//    }

    public News addNews(News news) {
        return newsRepository.save(news);
    }

    public List<News> getNewsByCategory(String category) {
        if ("All".equalsIgnoreCase(category)) {
            return newsRepository.findAll();
        } else {
            try {
                NewsCategory newsCategory = NewsCategory.valueOf(category.toUpperCase());
                return newsRepository.findByCategory(newsCategory);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid category: " + category);
            }        }
    }

    public void deleteNewsById(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new RuntimeException("News with ID " + id + " not found.");
        }
        newsRepository.deleteById(id);
    }

//    public News updateNews(Long id, String title, String content, String category, String imageUrl) {
//        try {
//            NewsCategory newsCategory = NewsCategory.valueOf(category.toUpperCase());
//            return newsRepository.findById(id).map(existingNews -> {
//                existingNews.setTitle(title);
//                existingNews.setContent(content);
//                existingNews.setCategory(newsCategory);
//                existingNews.setImageUrl(imageUrl);
//                return newsRepository.save(existingNews);
//            }).orElseThrow(() -> new RuntimeException("News with ID " + id + " not found."));
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("Invalid category: " + category);
//        }
//    }
    public News updateNews(Long id,News news) {
        if (newsRepository.existsById(id)) {
            news.setId(id);
            return newsRepository.save(news);
        }
        throw new RuntimeException("Exercise not found with id: " + id);
    }

}
