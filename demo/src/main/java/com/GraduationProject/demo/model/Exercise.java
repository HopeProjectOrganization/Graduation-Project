package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String excersiesId;

    private String name;
    private String gifUrl;
    private String bodyPart;
    private String target;
    private String equipment;

    @ElementCollection
    @CollectionTable(name = "exercise_secondary_muscles", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "muscle")
    private List<String> secondaryMuscles;

    @ElementCollection
    @CollectionTable(name = "exercise_instructions", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "instruction")
    private List<String> instructions;
}
