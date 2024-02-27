package br.com.onlineStore.orderms.application.useCasesImpl;

import br.com.onlineStore.orderms.adapters.http.CartClient;
import br.com.onlineStore.orderms.adapters.repository.OrderRepository;
import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.application.dto.ItemDto;
import br.com.onlineStore.orderms.application.dto.OrderDto;
import br.com.onlineStore.orderms.core.domain.Address;
import br.com.onlineStore.orderms.core.domain.ItemCart;
import br.com.onlineStore.orderms.core.domain.Order;
import br.com.onlineStore.orderms.core.domain.Status;
import br.com.onlineStore.orderms.core.useCases.MakeOrderUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MakeOrderUseCaseImpl implements MakeOrderUseCase {
    @Autowired
    private CartClient cartClient;
    @Autowired
    private OrderTotalPriceUseCaseImpl orderTotalPriceUseCase;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private KafkaTemplate<String, OrderDto> kafkaTemplate;
    @Autowired
    private ModelMapper mapper;

    @Override
    public OrderDto makeOrder(String email, AddressDto addressDto) {
        if (email != null) {
            var allItemsFromCartDto = cartClient.getAllItems(email);
            var allItemsFromCart = allItemsFromCartDto.stream().map(
                    itemDto -> mapper.map(itemDto, ItemCart.class)).collect(Collectors.toSet());
            var order = createOrder(allItemsFromCart, addressDto);

            orderRepository.save(order);
            mapper.map(order.getAddress(), AddressDto.class);

            var orderDto = mapper.map(order, OrderDto.class);

            kafkaTemplate.send("order-concluded", orderDto);

            return orderDto;
        }

        throw new IllegalArgumentException("email cannot be null or empty");
    }

    private Order createOrder(Set<ItemCart> allItemsFromCart, AddressDto addressDto) {
        var order = new Order();

        order.setItemCart(allItemsFromCart);
        var address = mapper.map(addressDto, Address.class);
        order.setAddress(address);
        order.setDate(OffsetDateTime.now().minusHours(3));
        order.setTrackingCode(UUID.randomUUID().toString());
        order.setStatus(Status.PROCESSING);
        var orderTotalPrice = orderTotalPriceUseCase.orderTotalPrice(allItemsFromCart.stream().map(
                item -> mapper.map(item, ItemDto.class)).collect(Collectors.toSet()));
        order.setValue(orderTotalPrice);

        return order;
    }
}