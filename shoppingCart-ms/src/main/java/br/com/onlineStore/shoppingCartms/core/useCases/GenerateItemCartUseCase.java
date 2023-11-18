package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.application.dto.PersistDto;

public interface GenerateItemCartUseCase {
    ItemCartDto generateItemCart(PersistDto dto, String token);
}
