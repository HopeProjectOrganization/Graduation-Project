package com.GraduationProject.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HereditaryPeople {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
//    private String content;
    @Enumerated(EnumType.STRING)
    private NewsCategory category;
    private String imageUrl;
    private LocalDate date;




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
