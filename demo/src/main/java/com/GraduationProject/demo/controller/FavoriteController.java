package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.Favorite;
import com.GraduationProject.demo.model.PostType;
import com.GraduationProject.demo.service.FavoriteService;
import com.GraduationProject.demo.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(
            @RequestBody Map<String, Object> requestBody,
            @AuthenticationPrincipal User user) {

        Integer postId = Integer.valueOf(requestBody.get("postId").toString());
        PostType postType = PostType.valueOf(requestBody.get("postType").toString());

        favoriteService.addToFavorites(user.getId(), postType, postId);
        return ResponseEntity.ok("Favorite added successfully.");
    }

    @GetMapping("/by-type/{type}")
    public ResponseEntity<List<Object>> getFavoritesByType(
            @PathVariable("type") String type,
            @AuthenticationPrincipal User user) {

        PostType postType = PostType.valueOf(type);

        List<Object> favorites = favoriteService.getFavoritesByType(user.getId(), postType);
        return ResponseEntity.ok(favorites);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFavorite(
            @RequestBody Map<String, Object> requestBody,
            @AuthenticationPrincipal User user) {

        Integer postId = Integer.valueOf(requestBody.get("postId").toString());
        PostType postType = PostType.valueOf(requestBody.get("postType").toString());

        favoriteService.deleteFavorite(user.getId(), postId, postType);
        return ResponseEntity.ok("Favorite deleted successfully.");
    }
}
