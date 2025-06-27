package com.GraduationProject.demo.service;


import com.GraduationProject.demo.model.Places;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        Product product1 =new Product();
        product1.setProductName(product.getProductName());
        product1.setBarcode(product.getBarcode());
        productRepository.save(product1);
        return product1;
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
