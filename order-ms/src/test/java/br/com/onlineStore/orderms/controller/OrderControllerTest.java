package br.com.onlineStore.orderms.controller;

import br.com.onlineStore.orderms.application.dto.OrderDto;
import br.com.onlineStore.orderms.core.domain.Address;
import br.com.onlineStore.orderms.core.domain.ItemCart;
import br.com.onlineStore.orderms.core.domain.Product;
import br.com.onlineStore.orderms.core.domain.Status;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderControllerTest {
    private OrderDto order;
    private String uri;

    @BeforeEach
    public void setUp(){
        ItemCart item1 = new ItemCart();
        item1.setId(1L);
        item1.setProduct(new Product());
        item1.setQuantity(2);

        ItemCart item2 = new ItemCart();
        item2.setId(2L);
        item2.setProduct(new Product());
        item2.setQuantity(3);

        Set<ItemCart> itemCartSet = new HashSet<>();
        itemCartSet.add(item1);
        itemCartSet.add(item2);

        Address address = new Address();
        address.setId(1L);
        address.setCep("12345-678");
        address.setState("Estado");
        address.setCity("Cidade");
        address.setNeighborhood("Bairro");
        address.setStreet("Rua");
        address.setComplement("Complemento");
        address.setNumber("123");

        order = new OrderDto();
        order.setId(1L);
        order.setItemCart(itemCartSet);
        order.setAddress(address);
        order.setDate(OffsetDateTime.now());
        order.setValue(100.0);
        order.setTrackingCode("ABC123");
        order.setStatus(Status.SENT);

        uri = "http://localhost:3100/order";
    }

    @Test
    void makeOrder() {
        given()
                .body(order)
                .cookie("email", "guilherme@email.com")
                .contentType(ContentType.JSON)
                .when()
                .post(uri)
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    void cancelOrder() {
        given()
                .pathParam("id", order.getId())
                .when()
                .delete(uri + "/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }
}