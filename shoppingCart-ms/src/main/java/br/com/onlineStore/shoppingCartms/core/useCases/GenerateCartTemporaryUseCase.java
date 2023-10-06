package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.shoppingCartms.application.dto.ShoppingCartDto;

public interface GenerateCartTemporaryUseCase {
    ShoppingCartDto createCart();
}