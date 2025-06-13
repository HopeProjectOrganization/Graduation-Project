package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String stringId;
    @ElementCollection
    private List<String> keywords;
    @ElementCollection
    private List<String> creator;
    @Column(length = 5000)
    private String description;
    private String title;
    private String link;
    @Column(length = 5000)
    private String content;
    private String pubDate;
    private String imageUrl;
    private String videoUrl;
    private String sourceId;
    private String sourceName;
    private int sourcePriority;
    private String sourceUrl;
    private String sourceIcon;
    private String language;
    @ElementCollection
    private List<String> country;
    @ElementCollection
    private List<String> category;
    private boolean duplicate;
    private String productName;
    private String barcode;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
}