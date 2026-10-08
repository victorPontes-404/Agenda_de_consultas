package com.portfolio.sistemaDeAgendamento.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.portfolio.sistemaDeAgendamento.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    private static final String ISSUER = "agendai_API";

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final long accessTokenMinutes;


    public TokenService (
        @Value("${agendai.jwt.secret.key}") String secretKey,
        @Value("${agendai.jwt.acess-token-minutes}") long accessTokenMinutes) {

        this.algorithm = Algorithm.HMAC256(secretKey);
        this.verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        this.accessTokenMinutes = accessTokenMinutes;
    }

    public String generateAccessToken(User user) {
        Instant now = Instant.now();

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().getValue())
                .withIssuedAt(now)
                .withExpiresAt(now.plus(accessTokenMinutes, ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    public String extractSubject(String token) {
        return verifier.verify(token).getSubject();
    }

    public DecodedJWT verify(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public long accessTokenSeconds() {
        return accessTokenMinutes * 60;
    }


}
