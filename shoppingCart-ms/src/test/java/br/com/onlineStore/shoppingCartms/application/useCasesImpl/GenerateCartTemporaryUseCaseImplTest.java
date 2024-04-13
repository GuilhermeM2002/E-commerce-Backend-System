package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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

import br.com.onlineStore.shoppingCartms.adapters.repository.ShoppingCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ShoppingCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GenerateCartTemporaryUseCaseImplTest {
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private GenerateCartTemporaryUseCaseImpl generateCartTemporaryUseCaseImpl;

    @Test
    @DisplayName("Should create cart temporary if not exists and return shoppingCart dto")
    void testGenerateCartTemporary() {
        var cart = new ShoppingCart();
        var cartDto = mock(ShoppingCartDto.class);
        var email = "email@email.com";
        var token = "token";

        when(shoppingCartRepository.findByToken(anyString())).thenReturn(null);
        when(shoppingCartRepository.save(cart)).thenReturn(cart);
        when(mapper.map(cart, ShoppingCartDto.class)).thenReturn(cartDto);

        var result = generateCartTemporaryUseCaseImpl.generateCartTemporary(token, email);

        assertAll(
                () -> assertEquals(cartDto, result),
                () -> assertNotNull(result),
                () -> verify(shoppingCartRepository).findByToken(anyString()),
                () -> verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class)),
                () -> verify(mapper).map(cart, ShoppingCartDto.class));
    }
}
