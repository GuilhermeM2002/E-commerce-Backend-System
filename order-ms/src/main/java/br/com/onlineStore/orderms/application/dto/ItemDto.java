package br.com.onlineStore.orderms.application.dto;

import br.com.onlineStore.orderms.core.domain.Cart;
import br.com.onlineStore.orderms.core.domain.Product;

public class ItemDto {
    private Long id;
    private Cart cart;
    private Product product;
    private int quantity;
}
