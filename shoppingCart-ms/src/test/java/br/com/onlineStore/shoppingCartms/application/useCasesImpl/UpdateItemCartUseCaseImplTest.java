package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UpdateItemCartUseCaseImplTest {
    @Mock
    private ItemCartRepository itemCartRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private UpdateItemCartUseCaseImpl updateItemCartUseCaseImpl;

    @Test
    @DisplayName("Existing product in the cart should update product and return mapped DataShoppingCard")
    void updateProductCartCase1() {
        var itemCart = mock(ItemCart.class);
        var dto = mock(ItemCartDto.class);
        var id = 1L;
        when(itemCartRepository.getReferenceById(id)).thenReturn(itemCart);
        when(mapper.map(itemCart, ItemCartDto.class)).thenReturn(dto);

        var result = updateItemCartUseCaseImpl.updateItemCart(dto, id);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(itemCartRepository).getReferenceById(id),
                () -> verify(mapper).map(itemCart, ItemCartDto.class));
    }

    @Test
    @DisplayName("Non existing product in the cart should throw IllegalArgumentException")
    void updateProductCartCase2() {
        var dto = mock(ItemCartDto.class);
        var id = 1L;
        when(itemCartRepository.getReferenceById(id)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> updateItemCartUseCaseImpl.updateItemCart(dto, id));
        verify(itemCartRepository).getReferenceById(id);
    }

}
