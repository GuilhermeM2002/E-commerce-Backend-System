package br.com.onlineStore.authenticationms.core.useCases;

import br.com.onlineStore.authenticationms.application.dto.SignInDto;
import br.com.onlineStore.authenticationms.application.dto.UserTokenDto;

public interface SignInUseCase {
    UserTokenDto signIn(SignInDto dto);
}
