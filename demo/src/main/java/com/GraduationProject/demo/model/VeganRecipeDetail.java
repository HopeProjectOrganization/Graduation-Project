package com.GraduationProject.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VeganRecipeDetail {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(unique = true)
        private String veganId;

        private String title;
        private String difficulty;
        private String portion;
        private String time;

//        @Column(length = 3000)
        private String description;

        private String image;

        @ElementCollection
        @CollectionTable(name = "vegan_recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
        @Column(name = "ingredient")
        private List<String> ingredients;

//        @ElementCollection
//        @CollectionTable(name = "vegan_recipe_steps", joinColumns = @JoinColumn(name = "recipe_id"))
//        @Column(name = "step")
//        private List<String> steps;

//    @Lob
//    @Column(name = "method_json", columnDefinition = "TEXT")
//    private String method;


    @ElementCollection
    @CollectionTable(name = "vegan_steps", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<Step> method;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Step {
        private String stepTitle;       // e.g., "Step 1"
        private String stepDescription; // e.g., "Melt the chocolate..."
    }

    }



