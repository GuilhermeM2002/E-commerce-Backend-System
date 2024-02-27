package br.com.onlineStore.orderms.application.useCasesImpl;

import br.com.onlineStore.orderms.adapters.repository.OrderRepository;
import br.com.onlineStore.orderms.core.domain.Status;
import br.com.onlineStore.orderms.core.useCases.CancelOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderUseCaseImpl implements CancelOrderUseCase {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void cancelOrder(Long id) {
        var order = orderRepository.getReferenceById(id);
        if (order.getValue() <= 200) {
            order.setStatus(Status.CANCELED);
        }
    }
}
