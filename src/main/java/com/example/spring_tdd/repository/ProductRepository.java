package com.example.spring_tdd.repository;

import com.example.spring_tdd.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
