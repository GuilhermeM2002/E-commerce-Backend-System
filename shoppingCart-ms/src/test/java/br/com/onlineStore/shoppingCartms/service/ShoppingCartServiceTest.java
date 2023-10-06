package br.com.onlineStore.shoppingCartms.service;

import br.com.onlineStore.shoppingCartms.application.useCasesImpl.UpdateCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;
import br.com.onlineStore.shoppingCartms.adapters.repository.ShoppingCartRepository;
import br.com.onlineStore.shoppingCartms.infra.ShoppingCartRepositoryService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {
    @InjectMocks
    private ShoppingCartRepositoryService service;
    @Mock
    private ModelMapper mapper;
    @Mock
    private ShoppingCartRepository repository;
    @Mock
    private UpdateCartUseCaseImpl update;
    private ShoppingCart cart;
    private ShoppingCartDto dto;
    private Long id;
    @BeforeEach
    void setUp(){
        cart = mock(ShoppingCart.class);
        dto = mock(ShoppingCartDto.class);
        id = 1L;
    }
    @Test
    @DisplayName("Should persist product in the cart and return mapped DataShoppingCart")
    void persistProductCart() {
        when(mapper.map(dto, ShoppingCart.class)).thenReturn(cart);
        when(repository.save(cart)).thenReturn(cart);
        when(mapper.map(cart, ShoppingCartDto.class)).thenReturn(dto);

        var result = service.persistProductCart(id);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(repository).save(cart),
                () -> verify(mapper).map(dto, ShoppingCart.class),
                () -> verify(mapper).map(cart, ShoppingCartDto.class)
        );
    }

    @Test
    @DisplayName("Existing product in the cart should update product and return mapped DataShoppingCard")
    void updateProductCartCase1() {
        when(repository.getReferenceById(id)).thenReturn(cart);
        when(mapper.map(cart, ShoppingCartDto.class)).thenReturn(dto);

        var result = service.updateProductCart(dto, id);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(repository).getReferenceById(id),
                () -> verify(mapper).map(cart, ShoppingCartDto.class)
        );
    }
    @Test
    @DisplayName("Non existing product in the cart should throw EntityNotFoundException")
    void updateProductCartCase2() {
        when(repository.getReferenceById(id)).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> service.updateProductCart(dto,id));
        verify(repository).getReferenceById(id);

    }

    @Test
    void allShoppingCart() {
        var pageable = Pageable.ofSize(10).withPage(0);
        var shoppingCart = Arrays.asList(
                cart,
                cart,
                cart
        );
        var page = new PageImpl<>(shoppingCart,pageable, shoppingCart.size());
        when(repository.findAll(pageable)).thenReturn(page);
        var result = service.allShoppingCart(pageable);

        assertAll(
                () -> assertEquals(3,result.getTotalElements()),
                () -> assertEquals(3, result.getContent().size()),
                () -> verify(repository).findAll(pageable)
        );
    }
}