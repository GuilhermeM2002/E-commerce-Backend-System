package br.com.onlineStore.orderms.core.useCases;

import br.com.onlineStore.orderms.application.dto.OrderDto;

public interface CancelOrderUseCase {
    void cancelOrder(Long id);

}
