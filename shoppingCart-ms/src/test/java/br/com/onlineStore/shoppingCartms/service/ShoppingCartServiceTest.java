package br.com.onlineStore.shoppingCartms.service;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.application.dto.ShoppingCartDto;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.UpdateItemCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;
import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
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
    private ItemCartRepository repository;
    private ItemCart itemCart;
    private ItemCartDto dto;
    private Long id;

    @BeforeEach
    void setUp() {
        itemCart = mock(ItemCart.class);
        dto = mock(ItemCartDto.class);
        id = 1L;
    }

    @Test
    void allShoppingCart() {
        var pageable = Pageable.ofSize(10).withPage(0);
        var shoppingCart = Arrays.asList(
                itemCart,
                itemCart,
                itemCart);
        var page = new PageImpl<>(shoppingCart, pageable, shoppingCart.size());
        when(repository.findAll(pageable)).thenReturn(page);
        var result = service.allShoppingCart(pageable);

        assertAll(
                () -> assertEquals(3, result.getTotalElements()),
                () -> assertEquals(3, result.getContent().size()),
                () -> verify(repository).findAll(pageable));
    }
}