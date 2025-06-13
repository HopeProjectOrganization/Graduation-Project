package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String recipeId;

    private String name;
    private String category;
    private String area;

    @Column(length = 5000)
    private String instructions;

    private String imageUrl;

    private String tags;
    private String youtubeUrl;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @MapKeyColumn(name = "ingredient")
    @Column(name = "measurement")
    private Map<String, String> ingredients = new HashMap<>();

    // Getters, setters, constructors
}

