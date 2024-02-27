package br.com.onlineStore.catalogms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
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

import br.com.onlineStore.catalogms.application.dto.ProductDto;
import br.com.onlineStore.catalogms.core.domain.Product;
import br.com.onlineStore.catalogms.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private UpdateProductUseCaseImpl updateProductUseCaseImpl;
    private ProductDto dto;
    private Product product;
    private Long code;

    @BeforeEach
    void setUp() {
        dto = mock(ProductDto.class);
        product = mock(Product.class);
        code = 1L;
    }
    
    @Test
    @DisplayName("Existing product should update product and return mapped DataProduct")
    void updateProductCase1() {
        when(productRepository.getReferenceById(code)).thenReturn(product);
        when(mapper.map(product, ProductDto.class)).thenReturn(dto);

        ProductDto result = updateProductUseCaseImpl.updateProduct(code, dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(productRepository).getReferenceById(code),
                () -> verify(mapper).map(product, ProductDto.class)
        );
    }
    @Test
    @DisplayName("Non existing product should throw EntityNotFoundException")
    void updateProductCase2() {
        when(productRepository.getReferenceById(code)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> updateProductUseCaseImpl.updateProduct(code, dto));
        verify(productRepository).getReferenceById(code);
    }
}
