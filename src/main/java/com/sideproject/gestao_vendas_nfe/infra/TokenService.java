package com.sideproject.gestao_vendas_nfe.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sideproject.gestao_vendas_nfe.domain.employee.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.Instant;



@Service
public class TokenService {

    @Value(value = "${api.security.token.secret}")
    private String secretKey;

    public TokenService(String secretKey){
        this.secretKey = secretKey;
    }

    public String generateToken(Employee employee) throws Exception{
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = com.auth0.jwt.JWT.create()
                    .withIssuer("auth0")
                    .withSubject(employee.getNome())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException exception){
            throw new Exception("Error while generating token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String verifyToken(String token) throws Exception{

        DecodedJWT decodedJWT;

        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            decodedJWT = verifier.verify(token);
        }

        catch(JWTVerificationException exception){
            throw new Exception("Error while validating token", exception);
        }

    }



}
