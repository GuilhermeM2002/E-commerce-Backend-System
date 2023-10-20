package br.com.onlineStore.authenticationms.application.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SignUpDto(
        @NotBlank
        String cpf,
        @NotBlank
        String name,
        @NotNull
        @Past
        LocalDate dateBirth,
        @NotNull
        String email,
        @NotNull
        String password
) {
}
