package br.com.onlineStore.authenticationms.controller;

import br.com.onlineStore.authenticationms.application.dto.SignInDto;
import io.restassured.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class SignInControllerTest {
    private SignInDto signInDto;
    private String uri;

    @BeforeEach
    public void setUp(){
        signInDto = new SignInDto();
        signInDto.setEmail("guilherme@email.com");
        signInDto.setPassword("password");

        uri = "http://localhost:39967/sign-in";
    }

    @Test
    void login() {
        Cookie cookie = new Cookie
                .Builder("cart-token", "NV2NlYsbiWnBE4PeNMtrtqdFp0-PBfmxMZd1O9F_gvM")
                .setSecured(true).build();
        given()
                .cookie(cookie)
                .when()
                .post(uri)
                .then()
                .statusCode(200)
                .cookie("email-cookie", equalTo(signInDto.getEmail()))
                .log().all();
    }
}