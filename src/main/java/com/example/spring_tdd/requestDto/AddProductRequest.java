package com.example.spring_tdd.requestDto;

import com.example.spring_tdd.entity.DiscountPolicy;
import org.springframework.util.Assert;

public class AddProductRequest {
    public String name;
    public int price;
    public DiscountPolicy discountPolicy;

    public AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }
}
