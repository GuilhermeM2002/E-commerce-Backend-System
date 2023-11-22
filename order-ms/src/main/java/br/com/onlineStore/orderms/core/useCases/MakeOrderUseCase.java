package br.com.onlineStore.orderms.core.useCases;

import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.application.dto.OrderDto;

public interface MakeOrderUseCase {
    OrderDto makeOrder (String email, AddressDto addressDto);
}
