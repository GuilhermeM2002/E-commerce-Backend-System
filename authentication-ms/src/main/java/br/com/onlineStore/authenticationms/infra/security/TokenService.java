package br.com.onlineStore.authenticationms.infra.security;

import br.com.onlineStore.authenticationms.core.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("{api.security.token.secret}")
    private String secret;

    public String tokenGenerator(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Vegano Space API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(dateExpires())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Error to the generate token.", exception);
        }
    }
    public String tokenVerification(String token){
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Vegano Space API")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalid or incorrect");
        }
    }
    private Instant dateExpires(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
