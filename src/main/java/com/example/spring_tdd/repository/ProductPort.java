package com.example.spring_tdd.repository;

import com.example.spring_tdd.entity.Product;

public interface ProductPort {
    void save(Product product);
}
