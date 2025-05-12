package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.DietCategory;
import com.GraduationProject.demo.model.HealthyDiet;
import com.GraduationProject.demo.repo.HealthyDietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthyDietService {

    private final HealthyDietRepository repository;

    public List<HealthyDiet> getAll() {
        return repository.findAll();
    }

    public List<HealthyDiet> getByCategory(DietCategory category) {
        return repository.findByCategory(category);
    }

    public HealthyDiet create(HealthyDiet diet) {
        return repository.save(diet);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public HealthyDiet update(Long id, HealthyDiet updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setImageUrl(updated.getImageUrl());
                    existing.setCategory(updated.getCategory());
//                    existing.setDate(updated.getDate());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Healthy diet post not found with ID " + id));
    }
}
