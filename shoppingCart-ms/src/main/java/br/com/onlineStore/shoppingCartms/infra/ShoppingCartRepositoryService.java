package br.com.onlineStore.shoppingCartms.infra;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartRepositoryService {
    @Autowired
    private ItemCartRepository itemCartRepository;
    @Autowired
    private ModelMapper mapper;

    public void deleteProductCart(Long id){
        itemCartRepository.deleteById(id);
    }

    public Page<ItemCartDto> allShoppingCart(Pageable pageable){
        return itemCartRepository.findAll(pageable)
                .map(product -> mapper.map(product, ItemCartDto.class));
    }
}
