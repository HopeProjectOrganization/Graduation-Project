package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.Exercise;
import com.GraduationProject.demo.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExerciseController {

    private final ExerciseService service;

    @PostMapping
    public ResponseEntity<Exercise> save(@RequestBody Exercise exercise) {
        return ResponseEntity.ok(service.save(exercise));
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable Integer id) {
        return service.getAll().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-string-id/{exId}")
    public ResponseEntity<Exercise> getByExerciseId(@PathVariable String exId) {
        return service.getByExerciseId(exId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(@PathVariable Integer id, @RequestBody Exercise updated) {
        try {
            return ResponseEntity.ok(service.updateById(id, updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
