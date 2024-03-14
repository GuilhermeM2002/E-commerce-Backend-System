package br.com.onlineStore.shoppingCartms.infra;

import br.com.onlineStore.shoppingCartms.adapters.repository.ItemCartRepository;
import br.com.onlineStore.shoppingCartms.application.dto.ItemCartDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

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
    public Cookie createCookie(){
        var cookie = new Cookie("cart-token", generateToken());
        cookie.setMaxAge(60 * 60 * 72); //three days
        return cookie;
    }
    private String generateToken() {
        var token = new SecureRandom();
        var randomByte = new byte[32];
        token.nextBytes(randomByte);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomByte);
    }
}
