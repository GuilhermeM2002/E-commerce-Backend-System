package br.com.onlineStore.shoppingCartms.controller;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ProductCart;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;
import br.com.onlineStore.shoppingCartms.core.domain.Status;
import br.com.onlineStore.shoppingCartms.infra.ShoppingCartRepositoryService;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartControllerTest {
    private ItemCartDto dto;
    private ShoppingCart shoppingCart;
    private ProductCart productCart;
    @Autowired
    private ShoppingCartRepositoryService service;
    private String url;
    OffsetDateTime date = OffsetDateTime.of(2024, 7, 26, 10, 15, 30, 0, ZoneOffset.UTC);

    @BeforeEach
    public void setUp(){
        shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setStatus(Status.ACTIVE);
        shoppingCart.setToken(service.generateToken());
        shoppingCart.setUserEmail("guilherme@email.com");
        shoppingCart.setDateCreation(date);

        productCart = new ProductCart();
        productCart.setCodeProduct(1L);
        productCart.setQuantity(1);
        productCart.setName("Name");
        productCart.setPrice(19.99);

        dto = new ItemCartDto();
        dto.setId(1L);
        dto.setShoppingCart(shoppingCart);
        dto.setProduct(productCart);
        dto.setQuantity(1);

        url = "http://localhost:3001/shoppingCart";
    }

    @Test
    void persist() {
        given()
                .body(dto)
                .contentType(ContentType.JSON)
                .when()
                .post(url)
                .then()
                .statusCode(201)
                .cookie("cart-token")
                .log().all();
    }

    @Test
    void update() {
        dto.setQuantity(2);
        given()
                .body(dto)
                .pathParam("id", dto.getId())
                .contentType(ContentType.JSON)
                .when()
                .put(url + "/{id}")
                .then()
                .statusCode(200)
                .body("quantity", equalTo(dto.getQuantity()))
                .log().all();
    }

    @Test
    void delete() {
        given()
                .pathParam("id", dto.getId())
                .when()
                .delete(url + "/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
    void findAll() {
        given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body("size( )", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("expectedSchemaItemCart.json"))
                .log().all();
    }

    @Test
    void findAllItemByUserEmail() {
        given()
                .pathParam("email", shoppingCart.getUserEmail())
                .when()
                .get(url + "/{email}")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("expectedSchemaItemCart.json"))
                .log().all();
    }
}