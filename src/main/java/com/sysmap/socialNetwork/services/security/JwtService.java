package com.sysmap.socialNetwork.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService implements IJwtService {

    private final long EXPIRATION_TIME = 7200000; //2 horas em milisegundos
    private final String KEY = "5367566B58703273357638792F423F4528482B4D6251655468576D5A71337436";

    public String generateToken(UUID userId) {
        return Jwts
                .builder()
                .setSubject(userId.toString()) //quem é o dono do token.
                .setIssuedAt(new Date(System.currentTimeMillis())) //Quando foi gerado.
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                //t.odo token de autenticação precisa de um prazo expiração.
                .signWith(genSignInKey(), SignatureAlgorithm.HS256) //Encripta o token.
                .compact(); //compacta e fecha o token
    }

    public boolean isValidToken(String token, String userId) {
        //////var claims = Jwts.parserBuilder().setSigningKey(genSignInKey()).build().parseClaimsJws(token).getBody();
        //Parse do token. passa chave (pega chave). build do token. pega as claims


        var sub = getClaim(token, Claims::getSubject);
        var tExpiration = getClaim(token, Claims::getExpiration);

        //---return (sub.equals(userId) && !tExpiration.before(new Date()));
        //Validar se o dono daquele token (subject) é o que eu to passando, e se a data de expiração não é anterior a data atual

        if (!sub.equals(userId)) {
            return false;
        }

        if (tExpiration.before(new Date())) {
            return false;
        }

        return true;
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        var claims = Jwts
                .parserBuilder()
                .setSigningKey(genSignInKey())
                .build()
                .parseClaimsJws(token) //TODO substring retirar 7 caracteres
                .getBody();

        return claimsResolver.apply(claims);
    }


    private Key genSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
        //Converte a chave para Base64 e depois para hmacSha
    }
}
