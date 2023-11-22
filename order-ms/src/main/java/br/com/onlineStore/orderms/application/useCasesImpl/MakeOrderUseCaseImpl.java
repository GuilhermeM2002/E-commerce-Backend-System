package br.com.onlineStore.orderms.application.useCasesImpl;

import br.com.onlineStore.orderms.adapters.http.CartClient;
import br.com.onlineStore.orderms.adapters.repository.OrderRepository;
import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.application.dto.OrderDto;
import br.com.onlineStore.orderms.core.domain.Address;
import br.com.onlineStore.orderms.core.domain.ItemCart;
import br.com.onlineStore.orderms.core.domain.Order;
import br.com.onlineStore.orderms.core.domain.Status;
import br.com.onlineStore.orderms.core.useCases.MakeOrderUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.Set;

public class MakeOrderUseCaseImpl implements MakeOrderUseCase {
    @Autowired
    private CartClient cartClient;
    @Autowired
    private OrderTotalPriceUseCaseImpl orderTotalPriceUseCase;
    @Autowired
    private RegisterAddressUseCaseImpl registerAddressUseCase;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public OrderDto makeOrder(String email, AddressDto addressDto) {
        if(email != null){
            var allItemsFromCartDto = cartClient.getAllItems(email);
            var allItemsFromCart = (Set<ItemCart>) allItemsFromCartDto.stream().map(item -> mapper.map(item, ItemCart.class));

            var order = new Order();
            order.setItemCart(allItemsFromCart);
            order.setAddress(
                    mapper.map(registerAddressUseCase.registerAddress(addressDto), Address.class));
            order.setDate(OffsetDateTime.now().minusHours(3));
            order.setStatus(Status.PROCESSING);
            order.setValue(orderTotalPriceUseCase.orderTotalPrice(allItemsFromCartDto));

            orderRepository.save(order);

            return mapper.map(order, OrderDto.class);
        }
        //make the exception
        throw new RuntimeException("email not be null");
    }
}