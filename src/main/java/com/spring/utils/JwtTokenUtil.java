package com.spring.utils;

import com.spring.model.security.JwtUserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_ROLE = "role";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String email;
        try {
            Claims claims = getClaimsFromToken(token);
            email = claims.getSubject();
            System.out.println("email : " + email);
        } catch (Exception e) {
            email = null;
        }
        return email;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getToken(JwtUserDetailsImpl userDetails) {
//        System.out.println("user name :" + userDetails.getEmail());
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getEmail());
        claims.put(CLAIM_KEY_CREATED, new Date());
        userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));

        return generateToken(claims);
    }

    public boolean validToken(String token) {
        return !expiredToken(token);
    }

    public String getToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, email);
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);
    }

    private Claims getClaimsFromToken(String token) throws AuthenticationException {

        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    private boolean expiredToken(String token) {
        Date expirationDate = this.getExpirationDateFromToken(token);
        if (expirationDate == null) {
            return false;
        }
        return expirationDate.before(new Date());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).
                setExpiration(generateExpirationDate()).
                signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
