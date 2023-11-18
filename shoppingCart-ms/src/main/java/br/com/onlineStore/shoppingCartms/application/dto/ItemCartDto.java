package br.com.onlineStore.shoppingCartms.application.dto;

import br.com.onlineStore.shoppingCartms.core.domain.ProductCart;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCartDto {
    private ProductCart product;
    private ShoppingCart shoppingCart;
    private int quantity;
}
