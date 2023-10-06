package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.shoppingCartms.adapters.repository.ProductRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ProductDto;
import br.com.onlineStore.shoppingCartms.core.domain.ProductCart;
import br.com.onlineStore.shoppingCartms.core.useCases.SaveProductUseCase;
import br.com.onlineStore.shoppingCartms.adapters.http.ProductClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveProductUseCaseImpl implements SaveProductUseCase {
    @Autowired
    private ProductClient productClient;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDto saveProduct(Long productId) {
        var product = productClient.getProduct(productId);
        productRepository.save(mapper.map(product, ProductCart.class));

        return mapper.map(product, ProductDto.class);
    }
}
