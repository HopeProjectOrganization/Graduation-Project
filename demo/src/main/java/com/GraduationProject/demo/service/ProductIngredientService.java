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



    public ProductIngredient updateProductIngredient(Integer id, String newIngredientName, String newPercentage) {

        ProductIngredient existingRelation = productIngredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductIngredient not found with id " + id));


        Ingredient ingredient = ingredientRepository.findByIngredientName(newIngredientName)
                .orElseGet(() -> {
                    Ingredient newIngredient = Ingredient.builder()
                            .ingredientName(newIngredientName)
                            .build();
                    return ingredientRepository.save(newIngredient);
                });


        existingRelation.setIngredient(ingredient);
        existingRelation.setPercentage(newPercentage);

        return productIngredientRepository.save(existingRelation);
    }

    public List<ProductIngredient> getAllProductIngredients() {
        return productIngredientRepository.findAll();
    }

    public ProductIngredient updateFullProductIngredient(Integer productIngredientId, Map<String, Object> data) {
        ProductIngredient relation = productIngredientRepository.findById(productIngredientId)
                .orElseThrow(() -> new RuntimeException("Relation not found"));


        Map<String, Object> productData = (Map<String, Object>) data.get("product");
        if (productData != null) {
            Product product = relation.getProduct();
            product.setProductName((String) productData.get("productName"));
            product.setBarcode((String) productData.get("barcode"));
            if (productData.get("productType") != null) {
                product.setProductType(ProductType.valueOf(((String) productData.get("productType")).toUpperCase()));
            }
            productRepository.save(product);
        }


        Map<String, Object> ingredientData = (Map<String, Object>) data.get("ingredient");
        if (ingredientData != null) {
            String newName = (String) ingredientData.get("ingredientName");
            Ingredient ingredient = ingredientRepository.findByIngredientName(newName)
                    .orElseGet(() -> ingredientRepository.save(Ingredient.builder().ingredientName(newName).build()));
            relation.setIngredient(ingredient);

            String newPercentage = (String) ingredientData.get("percentage");
            if (newPercentage != null) relation.setPercentage(newPercentage);
        }

        return productIngredientRepository.save(relation);
    }

}
