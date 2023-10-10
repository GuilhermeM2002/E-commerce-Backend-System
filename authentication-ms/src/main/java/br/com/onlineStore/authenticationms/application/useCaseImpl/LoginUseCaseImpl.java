package br.com.onlineStore.authenticationms.application.useCaseImpl;

import br.com.onlineStore.authenticationms.application.dto.LoginDto;
import br.com.onlineStore.authenticationms.core.domain.User;
import br.com.onlineStore.authenticationms.core.useCases.LoginUseCase;
import br.com.onlineStore.authenticationms.infra.config.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUseCase {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public String login(LoginDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getPassword());
        var authentication = authenticationManager.authenticate(authenticationToken);
        return tokenService.TokenGenerator((User) authentication.getPrincipal());
    }
}
