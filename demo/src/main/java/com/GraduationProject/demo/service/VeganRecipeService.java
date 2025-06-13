package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.VeganRecipeDetail;
import com.GraduationProject.demo.repo.VeganRecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VeganRecipeService {

    private final VeganRecipeRepository repository;

    public VeganRecipeService(VeganRecipeRepository repository) {
        this.repository = repository;
    }

    public VeganRecipeDetail save(VeganRecipeDetail recipe) {
        if (recipe.getVeganId() == null || recipe.getVeganId().isEmpty()) {
            recipe.setVeganId(UUID.randomUUID().toString());
        }
        return repository.save(recipe);
    }

    public Optional<VeganRecipeDetail> getByVeganId(String veganId) {
        return repository.findByVeganId(veganId);
    }

    public List<VeganRecipeDetail> getAll() {
        return repository.findAll();
    }

    public void deleteByVeganId(String veganId) {
        repository.findByVeganId(veganId).ifPresent(repository::delete);
    }
}

