package br.com.onlineStore.authenticationms.controller;

import br.com.onlineStore.authenticationms.application.dto.LoginDto;
import br.com.onlineStore.authenticationms.application.dto.RegisterDto;
import br.com.onlineStore.authenticationms.application.useCaseImpl.LoginUseCaseImpl;
import br.com.onlineStore.authenticationms.application.useCaseImpl.RegisterUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginUseCaseImpl loginUseCase;
    @Autowired
    private RegisterUseCaseImpl registerUseCase;
    @PostMapping
    public ResponseEntity login(@RequestBody @Validated LoginDto dto){
        return ResponseEntity.ok(loginUseCase.login(dto));
    }
    @PostMapping
    public ResponseEntity register(UriComponentsBuilder uriComponentsBuilder, @Validated @RequestBody RegisterDto dto){
        var uri = uriComponentsBuilder.path("/login/{id}").buildAndExpand(dto.getId()).toUri();
        var user = registerUseCase.register(dto);
        return ResponseEntity.created(uri).body(user);
    }
}
