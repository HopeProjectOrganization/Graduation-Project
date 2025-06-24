package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 5000)
    private String content;
    @Enumerated(EnumType.STRING)
    private NewsCategory category;
    private String imageUrl;



    private String articleId;

    private String link;

    @ElementCollection
    private List<String> keywords;

    @ElementCollection
    private List<String> creator;

    @Column(length = 5000)
    private String description;


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

    private boolean duplicate;


}
