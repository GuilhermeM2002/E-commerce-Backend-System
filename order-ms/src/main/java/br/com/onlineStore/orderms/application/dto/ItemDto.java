package br.com.onlineStore.orderms.application.dto;

import br.com.onlineStore.orderms.core.domain.Cart;
import br.com.onlineStore.orderms.core.domain.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemDto {
    private Long id;
    private Cart cart;
    private Product product;
    private int quantity;
}
