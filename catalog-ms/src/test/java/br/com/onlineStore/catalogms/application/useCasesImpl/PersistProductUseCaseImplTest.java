package br.com.onlineStore.catalogms.application.useCasesImpl;

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

import br.com.onlineStore.catalogms.application.dto.ProductDto;
import br.com.onlineStore.catalogms.core.domain.Product;
import br.com.onlineStore.catalogms.core.exception.PriceInvalidException;
import br.com.onlineStore.catalogms.repository.ProductRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersistProductUseCaseImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private PersistProductUseCaseImpl persistProductUseCaseImpl;

    @Test
    @DisplayName("Should persist product and return mapped DataProduct")
    void persistProductTestCase1() throws PriceInvalidException {
        var product = mock(Product.class);
        var dto = mock(ProductDto.class);
        when(dto.getPrice()).thenReturn(10.0);
        when(mapper.map(dto, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(mapper.map(product, ProductDto.class)).thenReturn(dto);

        ProductDto result = persistProductUseCaseImpl.persistProduct(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(mapper).map(dto, Product.class),
                () -> verify(productRepository).save(product),
                () -> verify(mapper).map(product, ProductDto.class));
    }

    @Test
    @DisplayName("Should throw exception if price is null")
    void persistProductTestCase2() {
        var dto = mock(ProductDto.class);
        assertThrows(PriceInvalidException.class,
                () -> persistProductUseCaseImpl.persistProduct(dto));
    }
}
