package br.com.onlineStore.authenticationms.application.useCasesImpl;

import br.com.onlineStore.authenticationms.application.dto.SignInDto;
import br.com.onlineStore.authenticationms.core.domain.User;
import br.com.onlineStore.authenticationms.core.useCases.SignInUseCase;
import br.com.onlineStore.authenticationms.infra.security.TokenService;
import br.com.onlineStore.authenticationms.application.dto.UserTokenDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SignInUseCaseImpl implements SignInUseCase {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
    @Override
    public UserTokenDto signIn(SignInDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.tokenGenerator((User) authentication.getPrincipal());

        kafkaTemplate.send("user-logged", mapper.map(dto, User.class));

        return new UserTokenDto(token);
    }
}
