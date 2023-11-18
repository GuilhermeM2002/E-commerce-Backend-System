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
    public ShoppingCartDto generateCartTemporary(String token) {
        var cart = shoppingCartRepository.findByToken(token);
        if (cart != null){
            return mapper.map(cart, ShoppingCartDto.class);
        }
        var newCart = generateCart();
        shoppingCartRepository.save(newCart);

        return mapper.map(newCart, ShoppingCartDto.class);
    }

    private ShoppingCart generateCart(){
        var cart = new ShoppingCart();
        cart.setToken(generateToken());
        cart.setDateCreation(OffsetDateTime.now().minusHours(3));
        cart.setStatus(Status.ACTIVE);

        return cart;
    }

    private String generateToken() {
        var token = new SecureRandom();
        var randomByte = new byte[32];
        token.nextBytes(randomByte);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomByte);
    }
}
