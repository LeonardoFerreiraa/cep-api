package br.com.leonardoferreira.cepapi.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.server.LocalServerPort;

public class BaseIntegrationTest {

    @LocalServerPort
    private Integer serverPort;

    @BeforeEach
    public void beforeEach() {
        RestAssured.port = serverPort;
    }
}
