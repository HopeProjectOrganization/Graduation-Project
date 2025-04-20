package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "HighRiskIngredients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HighRiskIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String ingredientName;

    @Column(nullable = false)
    private String riskLevel;

    private String safeLimit;

    @Column(nullable = false, length = 2000)
    private String English_description;

    @Column(nullable = false, length = 2000)
    private String Arabic_description;

}


