package br.com.onlineStore.authenticationms.application.useCaseImpl;

import br.com.onlineStore.authenticationms.adapters.repository.UserRepository;
import br.com.onlineStore.authenticationms.application.dto.RegisterDto;
import br.com.onlineStore.authenticationms.core.domain.User;
import br.com.onlineStore.authenticationms.core.useCases.RegisterUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RegisterUseCaseImpl implements RegisterUseCase {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public RegisterDto register(RegisterDto dto) {
        var user = mapper.map(dto, User.class);
        repository.save(user);
        return mapper.map(user, RegisterDto.class);
    }
}
