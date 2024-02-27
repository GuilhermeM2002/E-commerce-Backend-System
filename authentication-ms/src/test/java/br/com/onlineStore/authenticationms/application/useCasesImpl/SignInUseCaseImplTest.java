package br.com.onlineStore.authenticationms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.onlineStore.authenticationms.application.dto.SignInDto;
import br.com.onlineStore.authenticationms.application.dto.UserTokenDto;
import br.com.onlineStore.authenticationms.core.domain.User;
import br.com.onlineStore.authenticationms.infra.security.TokenService;
import br.com.onlineStore.common.SignIn;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SignInUseCaseImplTest {
    @Mock
    private TokenService tokenService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private ModelMapper mapper;
    @Mock
    private KafkaTemplate<String, SignIn> kafkaTemplate;
    @InjectMocks
    private SignInUseCaseImpl signInUseCase;

    @Test
    void testSignIn() {
        var signIn = mock(SignIn.class);
        var dto = new SignInDto();
        dto.setEmail("test@example.com");
        dto.setPassword("password");

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        
        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken(user, null));
        when(tokenService.tokenGenerator(any())).thenReturn("token");
        when(mapper.map(dto, SignIn.class)).thenReturn(signIn);

        UserTokenDto userTokenDto = signInUseCase.signIn(dto);

        assertEquals("token", userTokenDto.token());

        verify(kafkaTemplate, times(1)).send(anyString(), any(SignIn.class));
    }
}
