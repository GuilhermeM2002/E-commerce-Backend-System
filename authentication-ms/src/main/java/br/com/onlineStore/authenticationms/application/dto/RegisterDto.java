package br.com.onlineStore.authenticationms.application.dto;

import br.com.onlineStore.authenticationms.core.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private Long id;
    private String fullName;
    private String cpf;
    private String email;
    private String password;
    private LocalDate dateBirthday;
    private Set<Role> roles;
}
