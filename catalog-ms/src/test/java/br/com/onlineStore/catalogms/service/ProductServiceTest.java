package br.com.onlineStore.catalogms.service;

import br.com.onlineStore.catalogms.aplication.dto.ProductDto;
import br.com.onlineStore.catalogms.core.domain.Product;
import br.com.onlineStore.catalogms.infra.impl.ProductRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private br.com.onlineStore.catalogms.adapters.repository.ProductRepository repository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private ProductRepository service;
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
    @DisplayName("Should persist product and return mapped DataProduct")
    void persistProduct() {
        when(mapper.map(dto, Product.class)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        when(mapper.map(product, ProductDto.class)).thenReturn(dto);

        ProductDto result = service.persistProduct(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(mapper).map(dto, Product.class),
                () -> verify(repository).save(product),
                () -> verify(mapper).map(product, ProductDto.class)
        );
    }
    @Test
    @DisplayName("Existing product should update product and return mapped DataProduct")
    void updateProductCase1() {
        when(repository.getReferenceById(code)).thenReturn(product);
        when(mapper.map(product, ProductDto.class)).thenReturn(dto);

        ProductDto result = service.updateProduct(dto, code);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(repository).getReferenceById(code),
                () -> verify(mapper).map(product, ProductDto.class)
        );
    }
    @Test
    @DisplayName("Non existing product should throw EntityNotFoundException")
    void updateProductCase2() {
        when(repository.getReferenceById(code)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> service.updateProduct(dto, code));
        verify(repository).getReferenceById(code);
    }
    @Test
    @DisplayName("Should return a list off the Product")
    void findAllProduct(){
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        List<Product> products = Arrays.asList(
                product,
                product,
                product
        );
        Page<Product> page = new PageImpl<>(products, pageable, products.size());
        when(repository.findAll(pageable)).thenReturn(page);
        Page<ProductDto> result = service.findAllProduct(pageable);

        assertAll(
                () -> assertEquals(3,result.getTotalElements()),
                () -> assertEquals(3, result.getContent().size()),
                () -> verify(repository, times(1)).findAll(pageable)
        );
    }
    @Test
    @DisplayName("Should return one product")
    void findByCodeProductCase1(){
        when(repository.findById(code)).thenReturn(Optional.of(product));
        when(mapper.map(product, ProductDto.class)).thenReturn(dto);

        var result = service.findByCodeProduct(code);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto, result),
                () -> verify(repository).findById(code),
                () -> verify(mapper).map(product, ProductDto.class)
        );
    }

    @Test
    @DisplayName("Non returning product should throw EntityNotFoundException")
    void findByOneProductCase2(){
        when(repository.findById(code)).thenReturn(Optional.ofNullable(null));

        assertThrows(EntityNotFoundException.class, () -> service.findByCodeProduct(code));
        verify(repository).findById(code);
    }
}