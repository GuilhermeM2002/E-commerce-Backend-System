package br.com.onlineStore.shoppingCartms.infra;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.GenerateCartTemporaryUseCaseImpl;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.GenerateItemCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.application.useCasesImpl.UpdateCartUseCaseImpl;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;
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
    @Autowired
    private UpdateCartUseCaseImpl updateCart;
    @Autowired
    private GenerateCartTemporaryUseCaseImpl generateCartTemporary;
    @Autowired
    private GenerateItemCartUseCaseImpl generateItemCart;
    
    public ItemCartDto persistItemCart(Long idProduct, int quantity){
        var item = generateItemCart.generateItemCart(idProduct, quantity);

        itemCartRepository.save(mapper.map(item, ItemCart.class));

        return mapper.map(item, ItemCartDto.class);
    }

    public ItemCartDto updateItemCart(ItemCartDto dto, Long id){
        var itemCart = itemCartRepository.getReferenceById(id);

        updateCart.updateCart(dto, itemCart);
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
