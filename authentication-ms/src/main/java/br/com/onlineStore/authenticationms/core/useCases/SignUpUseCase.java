package br.com.onlineStore.authenticationms.core.useCases;

import br.com.onlineStore.authenticationms.application.dto.SignUpDto;
import br.com.onlineStore.authenticationms.application.dto.SignUpOutputDto;

public interface SignUpUseCase {
    SignUpOutputDto signUp(SignUpDto dto);
}
