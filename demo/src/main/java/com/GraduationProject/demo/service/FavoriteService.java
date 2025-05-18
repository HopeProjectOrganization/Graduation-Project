package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.Favorite;
import com.GraduationProject.demo.model.PostType;
import com.GraduationProject.demo.repo.*;
import com.GraduationProject.demo.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final HealthyDietRepository healthyDietRepository;
    private final HereditaryPeopleRepository hereditaryPeopleRepository;
    private final HighRiskPersonRepository highRiskPersonRepository;
    private final NewsRepository newsRepository;


    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, HealthyDietRepository healthyDietRepository, HereditaryPeopleRepository hereditaryPeopleRepository, HighRiskPersonRepository highRiskPersonRepository, NewsRepository newsRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.healthyDietRepository = healthyDietRepository;
        this.hereditaryPeopleRepository = hereditaryPeopleRepository;
        this.highRiskPersonRepository = highRiskPersonRepository;
        this.newsRepository = newsRepository;
    }

    public void addToFavorites(Integer userId, PostType postType, Integer postId) {
        if (favoriteRepository.existsByUserIdAndPostTypeAndPostId(userId, postType, postId)) {
            return; // already exists, don't add again
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setPostType(postType);
        favorite.setPostId(postId);

        favoriteRepository.save(favorite);
    }

    public List<Object> getFavoritesByType(Integer userId, PostType postType) {
//        return favoriteRepository.findByUserIdAndPostType(userId, postType);

        List<Favorite> favorites = favoriteRepository.findByUserIdAndPostType(userId, postType);
        List<Object> result = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Integer postId = favorite.getPostId();

            switch (postType) {
                case HEALTHY_DIET:
                    healthyDietRepository.findById(Long.valueOf(postId)).ifPresent(result::add);
                    break;

                case HEREDITARY_PEOPLE:
                    hereditaryPeopleRepository.findById(Long.valueOf(postId)).ifPresent(result::add);
                    break;

                case HIGH_RISK:
                    highRiskPersonRepository.findById(Long.valueOf(postId)).ifPresent(result::add);
                    break;

                case NEWS:
                    newsRepository.findById(Long.valueOf(postId)).ifPresent(result::add);
                    break;

                default:
                    break;
            }
        }

        return result;
    }

    public void deleteFavorite(Integer userId, Integer postId, PostType postType) {
        Optional<Favorite> favorite = favoriteRepository.findByUserIdAndPostIdAndPostType(userId, postId, postType);
        favorite.ifPresent(favoriteRepository::delete);
    }
}
