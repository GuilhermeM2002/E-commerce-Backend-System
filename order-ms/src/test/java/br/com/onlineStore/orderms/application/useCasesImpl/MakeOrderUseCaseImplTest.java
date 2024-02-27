package br.com.onlineStore.orderms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import br.com.onlineStore.orderms.adapters.http.CartClient;
import br.com.onlineStore.orderms.adapters.repository.OrderRepository;
import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.application.dto.ItemDto;
import br.com.onlineStore.orderms.application.dto.OrderDto;
import br.com.onlineStore.orderms.core.domain.Address;
import br.com.onlineStore.orderms.core.domain.Order;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MakeOrderUseCaseImplTest {
      @Mock
      private CartClient cartClient;
      @Mock
      private OrderRepository orderRepository;
      @Mock
      private OrderTotalPriceUseCaseImpl orderTotalPriceUseCase;
      @Mock
      private RegisterAddressUseCaseImpl registerAddressUseCase;
      @Mock
      private ModelMapper mapper;
      @Mock
      private KafkaTemplate<String, OrderDto> kafkaTemplate;
      @InjectMocks
      private MakeOrderUseCaseImpl makeOrderUseCaseImpl;

      @Test
      @DisplayName("Should save the order and return dto")
      void testMakeOrderCase1() {
            var orderDto = mock(OrderDto.class);
            var order = new Order();
            var addressDto = mock(AddressDto.class);
            var address = mock(Address.class);
            var email = "guilherme@email.com";

            when(mapper.map(any(Order.class), eq(OrderDto.class)))
                        .thenAnswer(invocation -> {
                              Order source = (Order) invocation.getArguments()[0];

                              orderDto.setId(source.getId());
                              orderDto.setAddress(source.getAddress());
                              orderDto.setDate(source.getDate());
                              orderDto.setItemCart(source.getItemCart());
                              orderDto.setStatus(source.getStatus());
                              orderDto.setTrackingCode(source.getTrackingCode());
                              orderDto.setValue(source.getValue());

                              return orderDto;
                        });

            doReturn(addressDto).when(mapper).map(any(Address.class), eq(AddressDto.class));
            doReturn(address).when(mapper).map(any(AddressDto.class), eq(Address.class));

            when(cartClient.getAllItems(any())).thenReturn(new HashSet<ItemDto>());
            when(orderRepository.save(any())).thenReturn(order);
            when(orderTotalPriceUseCase.orderTotalPrice(anySet())).thenReturn(123.0);
            when(kafkaTemplate.send(anyString(), any(OrderDto.class))).thenAnswer(invocation -> {
                  CompletableFuture<SendResult<String, String>> future = new CompletableFuture<>();
                  return future;
            });

            var result = makeOrderUseCaseImpl.makeOrder(email, addressDto);

            assertAll(
                        () -> assertNotNull(result),
                        () -> assertEquals(orderDto, result),
                        () -> verify(cartClient).getAllItems(anyString()),
                        () -> verify(orderRepository).save(order),
                        () -> verify(orderTotalPriceUseCase).orderTotalPrice(anySet()),
                        () -> verify(mapper, times(3)).map(any(), any()));
      }

      @Test
      @DisplayName("Should throw exception when email is empty or null")
      void testMakeOrderCase2() {
            String email = null;
            var addressDto = mock(AddressDto.class);

            assertThrows(IllegalArgumentException.class,
                        () -> makeOrderUseCaseImpl.makeOrder(email, addressDto));
      }
}
