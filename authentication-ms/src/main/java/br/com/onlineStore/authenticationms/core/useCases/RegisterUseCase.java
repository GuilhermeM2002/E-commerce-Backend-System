package br.com.onlineStore.authenticationms.core.useCases;

import br.com.onlineStore.authenticationms.application.dto.RegisterDto;

public interface RegisterUseCase {
    RegisterDto register(RegisterDto dto);
}
