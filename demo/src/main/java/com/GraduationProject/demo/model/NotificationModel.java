package com.GraduationProject.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Notification")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String topic;
    private String title;
    private String body;
    private String imageUrl;
    private String clickAction;

}