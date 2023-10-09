package br.com.onlineStore.authenticationms.controller;

import br.com.onlineStore.authenticationms.application.dto.LoginDto;
import br.com.onlineStore.authenticationms.application.useCaseImpl.LoginUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginUseCaseImpl loginUseCase;
    @PostMapping
    public ResponseEntity login(@RequestBody @Validated LoginDto dto){
        return ResponseEntity.ok(loginUseCase.login(dto));
    }
}
