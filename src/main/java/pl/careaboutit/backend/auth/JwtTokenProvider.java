package pl.careaboutit.backend.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.stereotype.Component;
import pl.careaboutit.backend.service.token.SessionTokenService;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    private final SessionTokenService sessionTokenService;

    public JwtTokenProvider(SessionTokenService sessionTokenService) {
        this.sessionTokenService = sessionTokenService;
    }

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        byte[] apiKeySecretBytes = Base64.getDecoder().decode(jwtSecret);
        SecretKey signingKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();

        sessionTokenService.saveToken(token, email, now, expiryDate);

        return token;
    }

    public OAuth2AuthenticatedPrincipal parseJwtToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", claims.getSubject());
        attributes.put("name", claims.get("name"));

        return new OAuth2IntrospectionAuthenticatedPrincipal(claims.getSubject(), attributes, null);
    }
}
