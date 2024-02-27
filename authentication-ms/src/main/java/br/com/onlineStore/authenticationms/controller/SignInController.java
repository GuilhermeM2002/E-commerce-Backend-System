package br.com.onlineStore.authenticationms.controller;

import br.com.onlineStore.authenticationms.application.dto.SignInDto;
import br.com.onlineStore.authenticationms.application.dto.UserTokenDto;
import br.com.onlineStore.authenticationms.application.useCasesImpl.SignInUseCaseImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-in")
public class SignInController {
    @Autowired
    private SignInUseCaseImpl signInUseCase;
    @PostMapping
    public ResponseEntity<UserTokenDto> login(
            @RequestBody @Valid SignInDto dto,
            @CookieValue(name = "cart-token", required = false) String token,
            HttpServletResponse response
    ){
        if(token != null){
           dto.setToken(token); 
        }
        var cookie = new Cookie("email-cookie", dto.getEmail());
        response.addCookie(cookie);
        return ResponseEntity.ok(signInUseCase.signIn(dto));
    }

}
