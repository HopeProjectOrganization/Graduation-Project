package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.HereditaryPeople;
import com.GraduationProject.demo.model.NewsCategory;
import com.GraduationProject.demo.service.HereditaryPeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hereditary")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class HereditaryPeopleController {

    private final HereditaryPeopleService service;

    @GetMapping
    public List<HereditaryPeople> getAllNews() {
        return service.getAllNews();
    }

    @GetMapping("/category/{category}")
    public List<HereditaryPeople> getByCategory(@PathVariable NewsCategory category) {
        return service.getByCategory(category);
    }

    @PostMapping
    public HereditaryPeople createNews(@RequestBody HereditaryPeople post) {
        return service.createNews(post);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        service.deleteNews(id);
    }

    @PutMapping("/{id}")
    public HereditaryPeople updateNews(@PathVariable Long id, @RequestBody HereditaryPeople updated) {
        return service.updateNews(id, updated);
    }
}
