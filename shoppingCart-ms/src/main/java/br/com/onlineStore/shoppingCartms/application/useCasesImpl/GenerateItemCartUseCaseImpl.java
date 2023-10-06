package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;
import br.com.onlineStore.shoppingCartms.core.domain.ProductCart;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;
import br.com.onlineStore.shoppingCartms.core.useCases.GenerateItemCartUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateItemCartUseCaseImpl implements GenerateItemCartUseCase  {
    @Autowired
    private ItemCartRepository itemCartRepository;
    @Autowired
    private GenerateCartTemporaryUseCaseImpl generateCartTemporary;
    @Autowired
    private SaveProductUseCaseImpl saveProduct;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ItemCartDto generateItemCart(Long productId, int quantity) {
        var item = new ItemCart();
        item.setProduct(mapper.map(
                saveProduct.saveProduct(productId), ProductCart.class));
        item.setShoppingCart(mapper.map(
                generateCartTemporary.createCart(), ShoppingCart.class));
        item.setQuantityOfProduct(quantity);

        itemCartRepository.save(item);

        return mapper.map(item, ItemCartDto.class);
    }
}
