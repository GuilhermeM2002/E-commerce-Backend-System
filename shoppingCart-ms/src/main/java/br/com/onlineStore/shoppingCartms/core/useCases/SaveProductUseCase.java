package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.shoppingCartms.application.dto.ProductDto;

public interface SaveProductUseCase {
    ProductDto saveProduct(Long productId);
}
