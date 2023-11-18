package br.com.onlineStore.shoppingCartms.core.useCases;

import br.com.onlineStore.common.SignIn;

public interface ConvertCartPermanentUseCase {
    void convertCart(SignIn signIn);
}