package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.Favorite;
import com.GraduationProject.demo.model.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserIdAndPostType(Integer userId, PostType postType);
    boolean existsByUserIdAndPostTypeAndPostId(Integer userId, PostType postType, Integer postId);

    Optional<Favorite> findByUserIdAndPostIdAndPostType(Integer userId, Integer postId, PostType postType);
}
