package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.HereditaryPeople;
import com.GraduationProject.demo.model.NewsCategory;
import com.GraduationProject.demo.repo.HereditaryPeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HereditaryPeopleService {

    private final HereditaryPeopleRepository repository;

    public List<HereditaryPeople> getAllNews() {
        return repository.findAll();
    }

    public List<HereditaryPeople> getByCategory(NewsCategory category) {
        return repository.findByCategory(category);
    }

    public HereditaryPeople createNews(HereditaryPeople post) {return repository.save(post);}

    public void deleteNews(Long id) {
        repository.deleteById(id);
    }

    public HereditaryPeople updateNews(Long id, HereditaryPeople updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setContent(updated.getContent());
                    existing.setImageUrl(updated.getImageUrl());
                    existing.setCategory(updated.getCategory());
                    existing.setDate(updated.getDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Post not found with ID " + id));
    }
}
