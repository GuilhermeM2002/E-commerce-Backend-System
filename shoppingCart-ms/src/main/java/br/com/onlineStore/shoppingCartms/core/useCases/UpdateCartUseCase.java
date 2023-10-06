package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;

public interface UpdateCartUseCase {
    void updateCart(ItemCartDto dto, ItemCart item);
}
