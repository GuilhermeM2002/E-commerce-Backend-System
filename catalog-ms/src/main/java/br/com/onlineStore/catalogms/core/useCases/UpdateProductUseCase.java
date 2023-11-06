package br.com.onlineStore.catalogms.core.useCases;

import br.com.onlineStore.catalogms.application.dto.ProductDto;
import br.com.onlineStore.catalogms.core.domain.Product;

public interface UpdateProductUseCase {
    void updateProduct(Product product, ProductDto dto);
    ProductDto updateProduct(Long code, ProductDto dto);
}
