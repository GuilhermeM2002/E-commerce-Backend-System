package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ItemCart;
import br.com.onlineStore.shoppingCartms.core.useCases.UpdateItemCartUseCase;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemCartUseCaseImpl implements UpdateItemCartUseCase {
    @Autowired
    private ItemCartRepository itemCartRepository;
    @Autowired
    private ModelMapper mapper;
    
    @Override
    public ItemCartDto updateItemCart(ItemCartDto dto, Long id) {
        var item = itemCartRepository.getReferenceById(id);
        validateInput(dto, item);
        item.updateItemFromDto(dto);

        return mapper.map(item, ItemCartDto.class);
    }

    private void validateInput(ItemCartDto dto, ItemCart cart) {
        if (dto == null || cart == null) {
            throw new IllegalArgumentException("DTO and cart must not be null");
        }
    }
}
