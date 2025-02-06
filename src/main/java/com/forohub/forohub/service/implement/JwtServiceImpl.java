package com.forohub.forohub.service.implement;

import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;
    public String generateToken(final Usuario usuario){
        return buildToken(usuario,jwtExpiration);
    }
    public String generateRefreshToken(final Usuario usuario){
        return buildToken(usuario,refreshExpiration);
    }
    public String extractUserName(final String token){
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getSubject();
    }
    public boolean isTokenValid(final String token, final Usuario usuario){
        final String userName = extractUserName(token);
        return (userName.equals(usuario.getCorreo())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token){
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(final String token){
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getExpiration();
    }
    private String buildToken(final Usuario usuario, final long expiration){
        return Jwts.builder()
                .id(usuario.getUuid())
                .claims(Map.of("name",usuario.getNombre()))
                .subject(usuario.getCorreo())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey())
                .compact();
    }
    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
