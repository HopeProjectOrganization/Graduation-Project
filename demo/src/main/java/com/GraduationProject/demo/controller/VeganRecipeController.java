package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.VeganRecipeDetail;
import com.GraduationProject.demo.repo.VeganRecipeRepository;
import com.GraduationProject.demo.service.VeganRecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vegan-recipes")
@CrossOrigin
public class VeganRecipeController {

    private final VeganRecipeService service;
    private final VeganRecipeRepository veganRecipeRepository;

    public VeganRecipeController(VeganRecipeService service, VeganRecipeRepository veganRecipeRepository) {
        this.service = service;
        this.veganRecipeRepository = veganRecipeRepository;
    }

    @PostMapping
    public ResponseEntity<VeganRecipeDetail> createRecipe(@RequestBody VeganRecipeDetail recipe) {
        VeganRecipeDetail saved = veganRecipeRepository.save(recipe);
        return ResponseEntity.ok(saved);
    }
//    public ResponseEntity<VeganRecipeDetail> create(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
//        VeganRecipeDetail recipe = new VeganRecipeDetail();
//
//        recipe.setTitle((String) payload.get("title"));
//        recipe.setDifficulty((String) payload.get("difficulty"));
//        recipe.setPortion((String) payload.get("portion"));
//        recipe.setTime((String) payload.get("time"));
//        recipe.setDescription((String) payload.get("description"));
//        recipe.setImage((String) payload.get("image"));
//        recipe.setIngredients((List<String>) payload.get("ingredients"));
//
//        // Store method as JSON string
//        ObjectMapper mapper = new ObjectMapper();
//        String methodJson = mapper.writeValueAsString(payload.get("method"));
//        recipe.setMethod(methodJson);
//
//        return ResponseEntity.ok(service.save(recipe));
//    }
    //Int
    @PutMapping("/by-id/{id}")
    public ResponseEntity<VeganRecipeDetail> updateById(@PathVariable Integer id, @RequestBody VeganRecipeDetail updatedRecipe) {
        return veganRecipeRepository.findById(id)
                .map(existing -> {
                    updatedRecipe.setId(id); // ensure ID consistency
                    return ResponseEntity.ok(veganRecipeRepository.save(updatedRecipe));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //String
    @PutMapping("/{veganId}")
    public ResponseEntity<VeganRecipeDetail> updateByVeganId(@PathVariable String veganId, @RequestBody VeganRecipeDetail updatedRecipe) {
        return veganRecipeRepository.findByVeganId(veganId)
                .map(existing -> {
                    updatedRecipe.setId(existing.getId()); // preserve database ID
                    updatedRecipe.setVeganId(veganId);     // ensure veganId consistency
                    return ResponseEntity.ok(veganRecipeRepository.save(updatedRecipe));
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @GetMapping
    public List<VeganRecipeDetail> getAll() {
        return service.getAll();
    }

    @GetMapping("/{veganId}")
    public ResponseEntity<VeganRecipeDetail> getByVeganId(@PathVariable String veganId) {
        return service.getByVeganId(veganId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET by Integer ID
    @GetMapping("/by-id/{id}")
    public ResponseEntity<VeganRecipeDetail> getById(@PathVariable Integer id) {
        return veganRecipeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE by Integer ID
    @DeleteMapping("/by-id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        if (veganRecipeRepository.existsById(id)) {
            veganRecipeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{veganId}")
    public ResponseEntity<Void> delete(@PathVariable String veganId) {
        service.deleteByVeganId(veganId);
        return ResponseEntity.noContent().build();
    }
}

