package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.DietCategory;
import com.GraduationProject.demo.model.HealthyDiet;
import com.GraduationProject.demo.service.HealthyDietService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet")
@RequiredArgsConstructor
public class HealthyDietController {

    private final HealthyDietService service;

    @GetMapping
    public List<HealthyDiet> getAll() {
        return service.getAll();
    }

    @GetMapping("/category/{category}")
    public List<HealthyDiet> getByCategory(@PathVariable DietCategory category) {
        return service.getByCategory(category);
    }

    @PostMapping
    public HealthyDiet create(@RequestBody HealthyDiet post) {
        return service.create(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public HealthyDiet update(@PathVariable Long id, @RequestBody HealthyDiet post) {
        return service.update(id, post);
    }
}
