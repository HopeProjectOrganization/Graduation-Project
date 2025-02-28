package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.Ingredient;
import com.GraduationProject.demo.repo.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;


    public Ingredient createIngrediant(Ingredient ingredient){
        Ingredient ingredient1 =new Ingredient();
        ingredient1.setIngredientName(ingredient.getIngredientName());
        ingredientRepository.save(ingredient1);
        return ingredient1;
    }


    public List<Ingredient> getAllIngredients(){

        return ingredientRepository.findAll();
    }
    public Ingredient getIngrediantById(Integer id){

        return ingredientRepository.findById(id).get();
    }

}
