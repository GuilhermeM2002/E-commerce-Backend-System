package br.com.onlineStore.authenticationms.infra.config;

import br.com.onlineStore.authenticationms.core.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("{api.security.token.secret}")
    private String secret;
    public String TokenGenerator(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("AuthenticationMsOnlineStore")
                    .withSubject(user.getEmail())
                    .withExpiresAt(dateExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error to the generate token.", exception);
        }
    }
    public DecodedJWT TokenVerifier(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("AuthenticationMsOnlineStore")
                    .build();

            return verifier.verify(token);
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalid or expired", exception);
        }
    }
    private Instant dateExpires(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
