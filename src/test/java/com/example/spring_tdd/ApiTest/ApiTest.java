package com.example.spring_tdd.ApiTest;

import com.example.spring_tdd.setting.DatabaseCleanUp;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

/*
본격적인 API 테스트 이전 설정정보
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {
    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() throws Exception {
        if(RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
            databaseCleanUp.afterPropertiesSet();
        }
        databaseCleanUp.execute();
    }
}
