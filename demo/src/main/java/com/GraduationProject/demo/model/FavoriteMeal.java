package com.GraduationProject.demo.model;


import com.GraduationProject.demo.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteMeal {

    @Id
    private String id;

    private String category;
    private String type;

    @ManyToOne
    private User user;
}
