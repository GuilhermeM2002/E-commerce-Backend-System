package br.com.onlineStore.catalogms.aplication.useCasesImpl;

import br.com.onlineStore.catalogms.adapters.repository.ProductRepository;
import br.com.onlineStore.catalogms.aplication.dto.ProductDto;
import br.com.onlineStore.catalogms.core.domain.Product;
import br.com.onlineStore.catalogms.core.useCases.UpdateProductUseCase;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public void updateProduct(Product product, ProductDto dto) {
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setCategory(dto.getCategory());
    }

    @Override
    public ProductDto updateProduct(Long code, ProductDto dto) {
        var product = repository.getReferenceById(code);
        if (product == null){
            throw new EntityNotFoundException();
        }
        updateProduct(product, dto);
        return mapper.map(product, ProductDto.class);
    }
}
