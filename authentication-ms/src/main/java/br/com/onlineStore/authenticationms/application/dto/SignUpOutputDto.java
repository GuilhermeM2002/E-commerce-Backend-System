package br.com.onlineStore.authenticationms.application.dto;

import br.com.onlineStore.authenticationms.core.domain.User;

import java.time.LocalDate;

public record SignUpOutputDto(Long id, String cpf, String name, LocalDate dateBirth) {
    public SignUpOutputDto(User user){
        this(user.getId(), user.getCpf(), user.getName(), user.getDateBirth());
    }
}
