package br.com.onlineStore.authenticationms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.onlineStore.authenticationms.adapters.repository.UserRepository;
import br.com.onlineStore.authenticationms.application.dto.SignUpDto;
import br.com.onlineStore.authenticationms.core.domain.User;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SignUpUseCaseImplTest {
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private SignUpUseCaseImpl signUpUseCase;

    @Test
    void testSignUp() {
        var dto = new SignUpDto(null, null, null, "joao.silva@example.com", "senhaSegura123");

        User user = new User(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        when(repository.save(any())).thenReturn(user);

        var result = signUpUseCase.signUp(dto);

        assertEquals(user.getEmail(), result.email());
        verify(repository, times(1)).save(any(User.class));
    }
}
