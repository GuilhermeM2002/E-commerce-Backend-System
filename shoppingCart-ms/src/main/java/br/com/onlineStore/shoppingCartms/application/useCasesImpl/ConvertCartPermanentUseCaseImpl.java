package br.com.onlineStore.shoppingCartms.application.useCasesImpl;

import br.com.onlineStore.common.SignIn;
import br.com.onlineStore.shoppingCartms.adapters.repository.ShoppingCartRepository;
import br.com.onlineStore.shoppingCartms.core.useCases.ConvertCartPermanentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConvertCartPermanentUseCaseImpl implements ConvertCartPermanentUseCase {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    @KafkaListener(topics = "user-logged", groupId = "group")
    public void convertCart(SignIn signIn) {
        var cart = shoppingCartRepository.findByToken(signIn.getToken());

        if (cart.getUserEmail() == null){
           cart.setUserEmail(signIn.getEmail());
        }
    }
}
