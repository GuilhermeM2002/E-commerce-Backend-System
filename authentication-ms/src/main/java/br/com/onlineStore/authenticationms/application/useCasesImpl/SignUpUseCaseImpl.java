package br.com.onlineStore.authenticationms.application.useCasesImpl;

import br.com.onlineStore.authenticationms.adapters.repository.UserRepository;
import br.com.onlineStore.authenticationms.application.dto.SignUpOutputDto;
import br.com.onlineStore.authenticationms.core.domain.User;
import br.com.onlineStore.authenticationms.core.useCases.SignUpUseCase;
import br.com.onlineStore.authenticationms.application.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpUseCaseImpl implements SignUpUseCase {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public SignUpOutputDto signUp(SignUpDto dto) {
        var user = new User(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return new SignUpOutputDto(user);
    }
}
