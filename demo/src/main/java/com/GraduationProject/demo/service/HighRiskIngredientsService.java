package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.HighRiskIngredients;
import com.GraduationProject.demo.repo.HighRiskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HighRiskIngredientsService {

    private final HighRiskRepo repository;

    public HighRiskIngredients addIngredient(HighRiskIngredients ingredient) {
        return repository.save(ingredient);
    }

    public List<HighRiskIngredients> getAllIngredients() {
        return repository.findAll();
    }

    public void deleteIngredient(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ingredient with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    public HighRiskIngredients updateIngredient(Integer id, HighRiskIngredients updatedIngredient) {
        HighRiskIngredients existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with id " + id));

        existing.setIngredientName(updatedIngredient.getIngredientName());
        existing.setRiskLevel(updatedIngredient.getRiskLevel());
        existing.setSafeLimit(updatedIngredient.getSafeLimit());
        existing.setEnglish_description(updatedIngredient.getEnglish_description());
        existing.setArabic_description(updatedIngredient.getArabic_description());

        return repository.save(existing);
    }
}
