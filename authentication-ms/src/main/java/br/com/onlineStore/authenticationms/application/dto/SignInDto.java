package br.com.onlineStore.authenticationms.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
        private String token;
}
