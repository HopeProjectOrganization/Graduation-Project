package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HighRiskPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
//    private String description;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private HighRiskCategory category;



    private String articleId;

//    private String title;

    private String link;

    @ElementCollection
    private List<String> keywords;

    @ElementCollection
    private List<String> creator;

    @Column(length = 5000)
    private String description;

    @Column(length = 5000)
    private String content;

    private String pubDate;

//    private String imageUrl;

    private String videoUrl;

    private String sourceId;

    private String sourceName;

    private int sourcePriority;

    private String sourceUrl;

    private String sourceIcon;

    private String language;

    @ElementCollection
    private List<String> country;

//    @ElementCollection
//    private List<String> category;

    private boolean duplicate;
}
