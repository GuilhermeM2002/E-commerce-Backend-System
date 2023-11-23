package br.com.onlineStore.orderms.core.useCases;

import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.application.dto.OrderDto;

import java.util.Set;

public interface MakeOrderUseCase {
    OrderDto makeOrder(String email, AddressDto addressDto);
}
