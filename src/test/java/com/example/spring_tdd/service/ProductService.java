package com.example.spring_tdd.service;

import com.example.spring_tdd.requestDto.AddProductRequest;
import com.example.spring_tdd.repository.ProductPort;
import com.example.spring_tdd.entity.Product;

public class ProductService {
    private ProductPort productPort;

    public void addProduct(AddProductRequest request) {
        Product product = new Product(request.name, request.price, request.discountPolicy);

        productPort.save(product);
    }
}
