package com.GraduationProject.demo.model;


import com.GraduationProject.demo.DTO.MealCategory;
import com.GraduationProject.demo.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    private LocalDate mealDate;

    @Enumerated(EnumType.STRING)
    private MealCategory category;

    private String mealId;
}