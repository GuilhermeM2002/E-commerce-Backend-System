package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;
import br.com.onlineStore.shoppingCartms.core.useCases.UpdateCartUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartUseCaseImpl implements UpdateCartUseCase {
    @Override
    public void updateCart(ItemCartDto dto, ItemCart item) {
        validateInput(dto, item);
        item.updateItemFromDto(dto);
    }

    private void validateInput(ItemCartDto dto, ItemCart cart) {
        if (dto == null || cart == null) {
            throw new IllegalArgumentException("DTO and cart must not be null");
        }
    }
}
