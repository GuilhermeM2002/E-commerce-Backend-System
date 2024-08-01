package br.com.onlineStore.catalogms.controller;

import br.com.onlineStore.catalogms.application.dto.ProductDto;
import br.com.onlineStore.catalogms.core.domain.Category;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest {
    private ProductDto product;
    private String uri;

    @BeforeEach
    public void setUp(){
        product = new ProductDto(
                1001L,
                "Smartphone",
                699.99,
                "Latest model smartphone",
                50,
                Category.ELECTRONIC);

        uri = "http://localhost:46141/product";
    }

    @Test
    void persist() {
        given()
                .body(product)
                .contentType(ContentType.JSON)
                .when()
                .post(uri)
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    void update() {
        product.setQuantity(2);
        given()
                .body(product)
                .pathParam("id", product.getCodeProduct())
                .contentType(ContentType.JSON)
                .when()
                .put(uri + "/{id}")
                .then()
                .statusCode(200)
                .body("quantity", equalTo(product.getQuantity()))
                .log().all();
    }

    @Test
    void delete() {
        given()
                .pathParam("id", product.getCodeProduct())
                .when()
                .delete(uri + "/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }

    @Test
    void getAll() {
        given()
                .when()
                .get(uri)
                .then()
                .statusCode(200)
                .body("size( )", greaterThan(0))
                .log().all();
    }

    @Test
    void getProduct() {
        given()
                .pathParam("code", product.getCodeProduct())
                .when()
                .get(uri + "/{code}")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("expectedSchemaProduct.json"))
                .log().all();
    }
}