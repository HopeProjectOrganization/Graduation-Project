package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    @Id
    private String id;

    private String name;
    private String description;
    private String image;
    private int prepareTime;
    private int cookTime;
    private int servings;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "meal_steps", joinColumns = @JoinColumn(name = "meal_id"))
    private List<String> steps;

    @ElementCollection
    @CollectionTable(name = "meal_ingredients", joinColumns = @JoinColumn(name = "meal_id"))
    private List<Ingredient> ingredients;

    @Embedded
    private Nutrients nutrients;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Ingredient {
        private String name;

        @Embedded
        private ServingSize servingSize;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ServingSize {
        private String units;
        private String description;
        private double qty;
        private Double grams;
        private double scale;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Nutrients {
        private double calories;
        private double netCarbs;
        private double protein;
        private double fat;
    }
}
