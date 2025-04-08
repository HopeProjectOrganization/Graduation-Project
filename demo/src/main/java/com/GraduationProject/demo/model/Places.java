package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Places")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Places {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hospitalName;
    private String hospitalNumber;
    private String  hospitalAddress;
    private String  hospitalLocation;
    private String  hospitalWebsite;
    private String Logo;


}
