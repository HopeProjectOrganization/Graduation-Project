package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.ProductType;
import com.GraduationProject.demo.model.Ingredient;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.model.ProductIngredient;
import com.GraduationProject.demo.repo.IngredientRepository;
import com.GraduationProject.demo.repo.ProductIngredientRepository;
import com.GraduationProject.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductIngredientService {
    private final ProductIngredientRepository productIngredientRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    public Product handleAddingProduct(Map<String, Object> productData) {
//        log.info("ProductRepository is null: {}", productRepository == null);
//        return null;

        String productName = (String) productData.get("productName");
        String barcode = (String) productData.get("barcode");
        String typeString = (String) productData.get("productType");
        ProductType productType = ProductType.valueOf(typeString.toUpperCase());
        List<Map<String, Object>> ingredientsData =
                (List<Map<String, Object>>) productData.get("ingredients");

        // Check if the product exists, or create a new one
        Optional<Product> existingProduct = productRepository.findByBarcode(barcode);
        Product product = existingProduct.orElseGet(() -> {
            Product newProduct = Product.builder()
                    .productName(productName)
                    .barcode(barcode)
                    .productType(productType)
                    .build();
            return productRepository.save(newProduct);
        });
        //  ingredient
        for (Map<String, Object> ingredientData : ingredientsData) {
            String ingredientName = (String) ingredientData.get("ingredientName");
            String percentage = (String) ingredientData.get("percentage");

            // Check if ingredient exists, or create a new one
            Optional<Ingredient> existingIngredient = ingredientRepository.findByIngredientName(ingredientName);
            Ingredient ingredient = existingIngredient.orElseGet(() -> {
                Ingredient newIngredient = Ingredient.builder()
                        .ingredientName(ingredientName)
                        .build();
                return ingredientRepository.save(newIngredient);
            });

            //  Check relationship create it
            boolean exists = productIngredientRepository.existsByProductAndIngredient(product, ingredient);
            if (!exists) {
                ProductIngredient productIngredient = ProductIngredient.builder()
                        .product(product)
                        .ingredient(ingredient)
                        .percentage(percentage)
                        .build();
                productIngredientRepository.save(productIngredient);
            }
        }

        return product;
    }

}
