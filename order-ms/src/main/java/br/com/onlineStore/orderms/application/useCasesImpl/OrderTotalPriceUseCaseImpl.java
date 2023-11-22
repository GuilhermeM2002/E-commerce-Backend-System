package br.com.onlineStore.orderms.application.useCasesImpl;

import br.com.onlineStore.orderms.application.dto.ItemDto;
import br.com.onlineStore.orderms.core.useCases.OrderTotalPriceUseCase;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class OrderTotalPriceUseCaseImpl implements OrderTotalPriceUseCase {
    @Override
    public double orderTotalPrice(Set<ItemDto> items) {
       var price = items.stream().map(item -> item.getProduct().getPrice());

        return price.mapToDouble(Double::doubleValue).sum();
    }
}
