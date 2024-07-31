package br.com.onlineStore.authenticationms.controller;

import br.com.onlineStore.authenticationms.application.dto.SignUpDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SignUpControllerTest {
    private SignUpDto signUpDto;
    private String uri;

    @BeforeEach
    public void setUp(){
                signUpDto = new SignUpDto(
                "12345678900",             // cpf
                "John Doe",                // name
                LocalDate.of(1990, 1, 1),  // dateBirth
                "john.doe@example.com",    // email
                "securePassword123"        // password
        );
        uri = "http://localhost:39967/sign-up";
    }

    @Test
    void persist() {
        given()
                .body(signUpDto)
                .when()
                .post(uri)
                .then()
                .statusCode(201)
                .log().all();
    }
}