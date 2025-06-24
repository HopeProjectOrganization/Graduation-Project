package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.HighRiskCategory;
import com.GraduationProject.demo.model.HighRiskPerson;
import com.GraduationProject.demo.model.NewsCategory;
import com.GraduationProject.demo.service.HighRiskPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/highrisk")
@RequiredArgsConstructor
public class HighRiskPersonController {

    private final HighRiskPersonService service;

    @GetMapping
    public List<HighRiskPerson> getAll() {
        return service.getAll();
    }

    @GetMapping("/category/{category}")
    public List<HighRiskPerson> getByCategory(@PathVariable HighRiskCategory category) {
        return service.getByCategory(category);
    }

    @PostMapping
    public HighRiskPerson create(@RequestBody HighRiskPerson person) {
        return service.create(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public HighRiskPerson update(@PathVariable Long id, @RequestBody HighRiskPerson person) {
        return service.update(id, person);
    }
}
