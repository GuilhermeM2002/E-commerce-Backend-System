package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;

public interface UpdateItemCartUseCase {
    ItemCartDto updateItemCart(ItemCartDto dto, Long id);
}
