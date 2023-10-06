package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;

public interface GenerateItemCartUseCase {
    ItemCartDto generateItemCart(Long productId, int quantity);
}
