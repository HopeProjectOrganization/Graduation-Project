package com.GraduationProject.demo.model;

import com.GraduationProject.demo.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private Integer postId;
}
