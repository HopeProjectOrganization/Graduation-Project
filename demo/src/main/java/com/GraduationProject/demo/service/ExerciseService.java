package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.Exercise;
import com.GraduationProject.demo.repo.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository repo;

    public Exercise save(Exercise exercise) {
        return repo.save(exercise);
    }

    public List<Exercise> getAll() {
        return repo.findAll();
    }

    public Optional<Exercise> getByExerciseId(String excersiesId) {
        return repo.findByExcersiesId(excersiesId);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public Exercise updateById(Integer id, Exercise updated) {
        if (repo.existsById(id)) {
            updated.setId(id);
            return repo.save(updated);
        }
        throw new RuntimeException("Exercise not found with id: " + id);
    }
}
