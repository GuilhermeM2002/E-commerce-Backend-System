package br.com.onlineStore.authenticationms.core.useCases;

import br.com.onlineStore.authenticationms.application.dto.LoginDto;

public interface LoginUseCase {
    String login(LoginDto dto);
}
