package br.com.onlineStore.catalogms.aplication.useCasesImpl;

import br.com.onlineStore.catalogms.adapters.repository.ProductRepository;
import br.com.onlineStore.catalogms.aplication.dto.ProductDto;
import br.com.onlineStore.catalogms.core.domain.Product;
import br.com.onlineStore.catalogms.infra.exception.PriceInvalidException;
import br.com.onlineStore.catalogms.core.useCases.PersistProductUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistProductUseCaseImpl implements PersistProductUseCase {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDto persistProduct(ProductDto dto) throws PriceInvalidException {
        if (dto.getPrice() <= 0){
            throw new PriceInvalidException();
        }
        var product = mapper.map(dto, Product.class);
        repository.save(product);

        return mapper.map(product, ProductDto.class);
    }
}
