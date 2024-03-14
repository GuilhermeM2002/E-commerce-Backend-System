package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.shoppingCartms.adapters.repository.ShoppingCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ShoppingCartDto;
import br.com.onlineStore.shoppingCartms.core.domain.ShoppingCart;
import br.com.onlineStore.shoppingCartms.core.domain.Status;
import br.com.onlineStore.shoppingCartms.core.useCases.GenerateCartTemporaryUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;
@Service
public class GenerateCartTemporaryUseCaseImpl implements GenerateCartTemporaryUseCase {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ShoppingCartDto generateCartTemporary(String token, String email) {
        var cart = shoppingCartRepository.findByToken(token);
        if (cart != null){
            if (cart.getUserEmail() == null && email != null){
                cart.setUserEmail(email);
                shoppingCartRepository.save(cart);
            }
            return mapper.map(cart, ShoppingCartDto.class);
        }
        var newCart = generateCart(token, email);
        shoppingCartRepository.save(newCart);

        return mapper.map(newCart, ShoppingCartDto.class);
    }

    private ShoppingCart generateCart(String token, String email){
        var cart = new ShoppingCart();
        cart.setToken(token);
        cart.setUserEmail(email);
        cart.setDateCreation(OffsetDateTime.now().minusHours(3));
        cart.setStatus(Status.ACTIVE);

        return cart;
    }


}
