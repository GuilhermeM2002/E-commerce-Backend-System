package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;

import java.util.Set;

public interface FindAllItemByUserEmailUseCase {
    Set<ItemCartDto> findAllItemByUserEmail(String email);
}
