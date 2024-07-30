package pl.careaboutit.backend.service.oauth.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.auth.GoogleTokenVerifier;
import pl.careaboutit.backend.auth.JwtTokenProvider;
import pl.careaboutit.backend.dto.oauth.EmailCheckResponseDto;
import pl.careaboutit.backend.dto.oauth.TokenDto;
import pl.careaboutit.backend.dto.oauth.UrlDto;
import pl.careaboutit.backend.exception.TechnicalException;
import pl.careaboutit.backend.service.oauth.OAuthService;
import pl.careaboutit.backend.service.user.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthServiceImpl implements OAuthService {

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;

    @Value("${custom-config.google.redirect-uri}")
    private String redirectUri;

    private final GoogleTokenVerifier googleTokenVerifier;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public UrlDto getAuthUrl() {
        String url = new GoogleAuthorizationCodeRequestUrl(
                clientId,
                redirectUri,
                Arrays.asList("email", "profile", "openid")
        ).build();

        log.info("Auth URL {}", url);

        return new UrlDto(url);
    }

    @Override
    public TokenDto callback(Map<String, String> requestBody) {
        String code = requestBody.get("code");
        String accessToken;
        String idToken;

        try {
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(), new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    redirectUri
            ).execute();
            accessToken = tokenResponse.getAccessToken();
            idToken = tokenResponse.getIdToken();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new TechnicalException("Error during Google token exchange", HttpStatus.UNAUTHORIZED);
        }

        log.info("Access Token: {}", accessToken);
        log.info("ID Token: {}", idToken);

        String email = getEmailFromIdToken(idToken);

        return new TokenDto(accessToken, email);
    }

    @Override
    public EmailCheckResponseDto checkEmail(String email) {
        boolean exists = userService.existsByEmail(email);

        return new EmailCheckResponseDto(exists);
    }

    @Override
    public TokenDto exchangeToken(TokenDto googleTokenDto) {
        log.debug("Received token: {}", googleTokenDto.token());

        String googleToken = googleTokenDto.token();

        if (googleToken == null || googleToken.trim().isEmpty()) {
            throw new TechnicalException("Invalid Google token", HttpStatus.BAD_REQUEST);
        }

        try {
            log.debug("Attempting to verify token: {}", googleToken);
            GoogleIdToken.Payload payload = googleTokenVerifier.verify(googleToken);

            if (payload == null) {
                log.warn("Token verification failed, payload is null");
                throw new TechnicalException("Token verification failed", HttpStatus.UNAUTHORIZED);
            }

            log.debug("Token verified successfully. Email: {}", payload.getEmail());
            String jwtToken = jwtTokenProvider.generateToken(payload.getEmail());

            return new TokenDto(jwtToken, payload.getEmail());

        } catch (Exception e) {
            log.error("Error exchanging Google token for JWT", e);
            throw new TechnicalException("Error exchanging token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getEmailFromIdToken(String idToken) {
        try {
            String[] splitToken = idToken.split("\\.");
            String payload = new String(Base64.getDecoder().decode(splitToken[1]));

            JsonObject jsonObject = new Gson().fromJson(payload, JsonObject.class);

            log.info("Email: {}", jsonObject.get("email").getAsString());

            return jsonObject.get("email").getAsString();
        } catch (Exception e) {
            log.error("Error parsing ID token: {}", e.getMessage());

            return null;
        }
    }

}
