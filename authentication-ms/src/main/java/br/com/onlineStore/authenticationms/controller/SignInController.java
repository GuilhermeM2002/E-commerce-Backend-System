package br.com.onlineStore.authenticationms.controller;

import br.com.onlineStore.authenticationms.application.dto.SignInDto;
import br.com.onlineStore.authenticationms.application.useCasesImpl.SignInUseCaseImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-in")
public class SignInController {
    @Autowired
    private SignInUseCaseImpl signInUseCase;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid SignInDto dto){
        return ResponseEntity.ok(signInUseCase.signIn(dto));
    }

}
