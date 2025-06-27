package com.GraduationProject.demo.repo;

import com.GraduationProject.demo.model.ActionType;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.model.UserProduct;
import com.GraduationProject.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {
    List<UserProduct> findByUserIdAndActionType(Integer userId, ActionType actionType);
    boolean existsByUserAndProductAndActionType(User user, Product product, ActionType actionType);

    void deleteByProductId(Integer id);
    
}
