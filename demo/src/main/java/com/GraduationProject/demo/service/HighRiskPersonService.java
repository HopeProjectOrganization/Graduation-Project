package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.HighRiskPerson;
import com.GraduationProject.demo.model.NewsCategory;
import com.GraduationProject.demo.repo.HighRiskPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HighRiskPersonService {

    private final HighRiskPersonRepository repository;

    public List<HighRiskPerson> getAll() {
        return repository.findAll();
    }

    public List<HighRiskPerson> getByCategory(NewsCategory category) {
        return repository.findByCategory(category);
    }

    public HighRiskPerson create(HighRiskPerson person) {
        return repository.save(person);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public HighRiskPerson update(Long id, HighRiskPerson updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(updated.getTitle());
                    existing.setDescription(updated.getDescription());
                    existing.setImageUrl(updated.getImageUrl());
                    existing.setCategory(updated.getCategory());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("High risk person not found with ID " + id));
    }
}
