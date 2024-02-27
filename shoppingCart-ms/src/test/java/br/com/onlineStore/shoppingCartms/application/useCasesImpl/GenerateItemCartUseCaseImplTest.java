package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.application.dto.PersistDto;
import br.com.onlineStore.shoppingCartms.application.dto.ProductDto;
import br.com.onlineStore.shoppingCartms.application.dto.ShoppingCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;
import br.com.onlineStore.shoppingCartms.core.domain.ProductCart;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GenerateItemCartUseCaseImplTest {
        @InjectMocks
        private GenerateItemCartUseCaseImpl generateItemCartUseCase;
        @Mock
        private ItemCartRepository itemCartRepository;
        @Mock
        private GenerateCartTemporaryUseCaseImpl generateCartTemporary;
        @Mock
        private SaveProductUseCaseImpl saveProduct;
        @Mock
        private ModelMapper mapper;

        @Test
        @DisplayName("Should save item with product, cart and quantity as well as return item dto")
        public void testGenerateItemCart() {
                var persistDto = new PersistDto(1L, 5);

                String token = "token";

                var productCart = new ProductCart();
                var productCartDto = mock(ProductDto.class);
                var shoppingCart = new ShoppingCart();
                var shoppingCartDto = mock(ShoppingCartDto.class);
                var itemCart = new ItemCart();
                var itemCartDto = mock(ItemCartDto.class);

                itemCart.setProduct(productCart);
                itemCart.setShoppingCart(shoppingCart);
                itemCart.setQuantityOfProduct(persistDto.quantity());

                when(saveProduct.saveProduct(anyLong())).thenReturn(productCartDto);
                when(mapper.map(productCartDto, ProductCart.class))
                                .thenReturn(productCart);
                when(generateCartTemporary.generateCartTemporary(token)).thenReturn(shoppingCartDto);
                when(mapper.map(shoppingCartDto, ShoppingCart.class))
                                .thenReturn(shoppingCart);
                when(itemCartRepository.save(itemCart)).thenReturn(itemCart);
                when(mapper.map(itemCart, ItemCartDto.class))
                                .thenReturn(itemCartDto);

                ItemCartDto result = generateItemCartUseCase.generateItemCart(persistDto, token);

                assertAll(
                                () -> assertNotNull(result),
                                () -> assertEquals(itemCartDto, result),
                                () -> verify(itemCartRepository, times(1)).save(any(ItemCart.class)),
                                () -> verify(saveProduct).saveProduct(anyLong()),
                                () -> verify(generateCartTemporary).generateCartTemporary(token),
                                () -> verify(mapper).map(productCartDto, ProductCart.class),
                                () -> verify(mapper).map(shoppingCartDto, ShoppingCart.class),
                                () -> verify(mapper).map(itemCart, ItemCartDto.class));

        }
}
