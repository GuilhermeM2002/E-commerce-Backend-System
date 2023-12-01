package br.com.onlineStore.orderms.core.useCases;

import br.com.onlineStore.orderms.application.dto.ItemDto;

import java.util.Set;

public interface OrderTotalPriceUseCase {
    double orderTotalPrice(Set<ItemDto> items);
}
