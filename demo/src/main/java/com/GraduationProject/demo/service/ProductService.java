package com.GraduationProject.demo.service;


import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.repo.ProductIngredientRepository;
import com.GraduationProject.demo.repo.ProductRepository;
import com.GraduationProject.demo.repo.UserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UserProductRepository userProductRepository;
    private final ProductRepository productRepository;
    private final ProductIngredientRepository productIngredientRepository;

    public Product createProduct(Product product){
        Product product1 =new Product();
        product1.setProductName(product.getProductName());
        product1.setBarcode(product.getBarcode());
        productRepository.save(product1);
        return product1;
    }

    @Transactional
    public void deleteProductById(Integer id) {
        productIngredientRepository.deleteByProductId(id);
        userProductRepository.deleteByProductId(id);

        // Using JpaRepository.deleteById(id) — throws exception if not found
        productRepository.deleteById(id);
    }


    public  List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Integer id){

        return productRepository.findById(id).get();
    }
    public Product getProductByBarCode(String barcode){

        return productRepository
                .findByBarcode(barcode)
                .orElseThrow(() ->
                        new RuntimeException("Product not found with barcode: " + barcode));
    }


}
