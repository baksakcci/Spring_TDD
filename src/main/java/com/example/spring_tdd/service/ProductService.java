package com.example.spring_tdd.service;

import com.example.spring_tdd.requestDto.AddProductRequest;
import com.example.spring_tdd.repository.ProductPort;
import com.example.spring_tdd.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Service -> Service + RestController
RestController는 Network를 통해 통신하여 가져오는 역할 담당
 */
@RestController
@RequestMapping("/products")
public class ProductService {
    @Autowired
    private ProductPort productPort;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> addProduct(@RequestBody AddProductRequest request) {
        Product product = new Product(request.name, request.price, request.discountPolicy);

        productPort.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
