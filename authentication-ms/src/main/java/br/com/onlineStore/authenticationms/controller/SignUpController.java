package br.com.onlineStore.authenticationms.controller;

import br.com.onlineStore.authenticationms.application.dto.SignUpDto;
import br.com.onlineStore.authenticationms.application.useCasesImpl.SignUpUseCaseImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SignUpController {
    @Autowired
    private SignUpUseCaseImpl signUpUseCase;

    @PostMapping("/sign-up")
    @Transactional
    public ResponseEntity persist(@RequestBody @Valid SignUpDto dto, UriComponentsBuilder uriBuilder){
        var user = signUpUseCase.signUp(dto);
        var uri = uriBuilder.path("/sign-up/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }
}
