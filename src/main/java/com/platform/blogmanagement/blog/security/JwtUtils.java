package com.platform.blogmanagement.blog.security;
import com.platform.blogmanagement.blog.user.dto.AuthTokenResponse;
import com.platform.blogmanagement.blog.user.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.signatureAlgorithm}")
    private String signAlgo;
    @Value("${jwt.expirationTimeout}")
    private String expiration;
    public Optional<String> getUserIdFromJwtToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        boolean valid = !claims.getExpiration().before(new Date());

        if(!valid) {
            return Optional.empty();
        }
        String userId = claims.getId();
        return Optional.of(userId);
    }

    public AuthTokenResponse generateTokenFromUserDetails(UserModel userDetail) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Authorities", userDetail.getAuthorities());
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetail.getUserFullName())
                .setId(userDetail.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(expiration)))
                .signWith(SignatureAlgorithm.valueOf(signAlgo), secretKey).compact();
        AuthTokenResponse authTokenResponse = new AuthTokenResponse(token, "Bearer", Integer.parseInt(expiration));
        log.info(authTokenResponse.toString());
        return authTokenResponse;

    }
}
