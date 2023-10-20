package br.com.onlineStore.authenticationms.application.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInDto(
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
