package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import br.com.onlineStore.shoppingCartms.core.useCases.FindAllItemByUserEmailUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class FindAllItemByUserEmailUseCaseImpl implements FindAllItemByUserEmailUseCase {
    @Autowired
    private ItemCartRepository itemCartRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public Set<ItemCartDto> findAllItemByUserEmail(String email) {
        var allItem = itemCartRepository.findByShoppingCart_UserEmail(email);

        return allItem.stream().map(item -> mapper.map(
            item, ItemCartDto.class)).collect(Collectors.toSet());
    }
}
