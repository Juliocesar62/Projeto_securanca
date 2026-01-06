package com.api.cadastropessoaevolution.securityproject.service;

import com.api.cadastropessoaevolution.securityproject.dto.TokenPessoaDTO;
import com.api.cadastropessoaevolution.securityproject.model.Usuario;
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

    @Value("${api.security.token.secret}")
    private String secret;

    public String generationToken(Usuario usuario) {
        try {

            TokenPessoaDTO pessoaDTO = validadePessoa(usuario);

            Algorithm algorithm = Algorithm.HMAC384(secret);

            return JWT.create()
                    .withIssuer("autenticar_usuarios")
                    .withSubject(usuario.getLogin())
                    .withClaim("id_pessoa", pessoaDTO.getIdPessoa())
                    .withClaim("role", usuario.getRole().name())
                    .withExpiresAt(generetionExperition())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("ERRO NA GERAÇÃO DO TOKEN", e);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC384(secret);
            return JWT.require(algorithm)
                    .withIssuer("autenticar_usuarios")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("ERRO NA VERIFICAÇÂO DO TOKEN" , e);
        }
    }

    private Instant generetionExperition(){
        return LocalDateTime.now().plusHours(10).toInstant(ZoneOffset.of("-03:00"));
    }

    public TokenPessoaDTO validadePessoa(Usuario usuario){
        TokenPessoaDTO pessoaDTO = new TokenPessoaDTO();

        if(usuario.getAtendente() == null){
            pessoaDTO.setIdPessoa(usuario.getCliente().getId());
        }
        else {
           pessoaDTO.setIdPessoa(usuario.getAtendente().getId());
        }
        return pessoaDTO;
    }
}
