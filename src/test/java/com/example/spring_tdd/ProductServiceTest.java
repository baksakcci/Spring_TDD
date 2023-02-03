package com.example.spring_tdd;

import com.example.spring_tdd.entity.DiscountPolicy;
import com.example.spring_tdd.repository.ProductAdapter;
import com.example.spring_tdd.repository.ProductPort;
import com.example.spring_tdd.repository.ProductRepository;
import com.example.spring_tdd.requestDto.AddProductRequest;
import com.example.spring_tdd.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
POJO 구현
 */
class ProductServiceTest {

    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;
    @BeforeEach
    void setup() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository); // Repository를 JPA Repository로 바꿀수 있음
        productService = new ProductService();
    }

    @Test
    void addProductTest() {
        final String name = "사과";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;

        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        productService.addProduct(request);
    }
}
