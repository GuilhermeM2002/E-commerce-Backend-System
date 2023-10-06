package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.shoppingCartms.adapters.repository.ShoppingCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ShoppingCartDto;
import br.com.onlineStore.shoppingCartms.core.useCases.FindCartUsingTemporaryTokenUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindCartUsingTemporaryTokenUseCaseImpl implements FindCartUsingTemporaryTokenUseCase {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ShoppingCartDto findCartByToken(String token) {
        var cart = shoppingCartRepository.findByToken(token);

        return mapper.map(cart, ShoppingCartDto.class);
    }
}
