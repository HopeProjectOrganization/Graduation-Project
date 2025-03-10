package com.GraduationProject.demo.service;


import com.GraduationProject.demo.model.ActionType;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.model.UserProduct;
import com.GraduationProject.demo.repo.ProductRepository;
import com.GraduationProject.demo.repo.UserProductRepository;
import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final UserProductRepository userProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void addUserProduct(Integer userId, String barcode, ActionType actionType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        boolean exists = userProductRepository.existsByUserAndProductAndActionType(user, product, actionType);
        if (exists) {
            return;
//            throw new RuntimeException("This product has already been " + actionType.name().toLowerCase() + " by the user.");
        }

        UserProduct userProduct = UserProduct.builder()
                .user(user)
                .product(product)
                .actionType(actionType)
                .build();

        userProductRepository.save(userProduct);
    }

    public List<Product> getProductsByAction(Integer userId, ActionType actionType) {
        return userProductRepository.findByUserIdAndActionType(userId, actionType)
                .stream()
                .map(UserProduct::getProduct)
                .toList();
    }

}
