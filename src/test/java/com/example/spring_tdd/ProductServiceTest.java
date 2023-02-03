package com.example.spring_tdd;

import com.example.spring_tdd.entity.DiscountPolicy;
import com.example.spring_tdd.repository.ProductAdapter;
import com.example.spring_tdd.repository.ProductPort;
import com.example.spring_tdd.repository.ProductRepository;
import com.example.spring_tdd.requestDto.AddProductRequest;
import com.example.spring_tdd.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void addProductTest() {
        final String name = "사과";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;

        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);

        productService.addProduct(request);
    }
}
