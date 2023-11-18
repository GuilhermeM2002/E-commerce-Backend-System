package br.com.onlineStore.shoppingCartms.infra;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.application.dto.PersistDto;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.GenerateItemCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.UpdateItemCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartRepositoryService {
    @Autowired
    private ItemCartRepository itemCartRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UpdateItemCartUseCaseImpl updateItemCart;
    @Autowired
    private GenerateItemCartUseCaseImpl generateItemCart;
    public ItemCartDto updateItemCart(ItemCartDto dto, Long id){
        var itemCart = itemCartRepository.getReferenceById(id);

        updateItemCart.updateItemCart(dto, itemCart);
        return mapper.map(itemCart, ItemCartDto.class);
    }

    public void deleteProductCart(Long id){
        itemCartRepository.deleteById(id);
    }

    public Page<ItemCartDto> allShoppingCart(Pageable pageable){
        return itemCartRepository.findAll(pageable)
                .map(product -> mapper.map(product, ItemCartDto.class));
    }
}
