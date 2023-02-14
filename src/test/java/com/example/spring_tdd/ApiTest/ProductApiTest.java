package com.example.spring_tdd.ApiTest;

import com.example.spring_tdd.entity.DiscountPolicy;
import com.example.spring_tdd.requestDto.AddProductRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;


/*
이전 설정은 ApiTest로부터 설정 완료됨
1. 사용자로부터 온 요청[DTO] 객체 만들기
2. 요청에 대한 서버의 응답 만들기[ExtractableResponse<Response>] <- rest-assure 라이브러리 활용
3. 응답 코드가 CREATED(201)이 맞는지 확인[assertThat]

JPA 적용 후
TEST 안에서 JPA가 직접 테이블 생성

rest-assure 라이브러리의 문제점
spring application을 실행하면 caching이 이루어져서
다른 테스트를 생성, 수정, 삭제 등을 할 시 꼬일수가 있다.
테스트를 정리하는 방법은 -> 우아한테크세미나의 우아한ATDD 참고
 */
public class ProductApiTest extends ApiTest {
    @Test
    void addProductTest() {
        // 상품 생성
        final String name = "사과";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        // 상품 등록 요청 생성
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        // API 요청 (상품 등록 요청에 대한 응답)(메서드로 추출함)
        ExtractableResponse<Response> response = requestProduct(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    /*
    API 테스트용 응답 객체 생성
     */
    private ExtractableResponse<Response> requestProduct(AddProductRequest request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
        return response;
    }
}
