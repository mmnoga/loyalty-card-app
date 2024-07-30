package pl.careaboutit.backend.controller.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.auth.GoogleTokenVerifier;
import pl.careaboutit.backend.auth.JwtTokenProvider;
import pl.careaboutit.backend.dto.oauth.EmailCheckResponseDto;
import pl.careaboutit.backend.dto.oauth.TokenDto;
import pl.careaboutit.backend.dto.oauth.UrlDto;
import pl.careaboutit.backend.service.oauth.OAuthService;
import pl.careaboutit.backend.service.user.UserService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final OAuthService authService;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;

    @Value("${custom-config.google.redirect-uri}")
    private String redirectUri;

    private final GoogleTokenVerifier googleTokenVerifier;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @GetMapping("/auth/url")
    public ResponseEntity<UrlDto> getAuthUrl() {
        return ResponseEntity.ok(authService.getAuthUrl());
    }

    @PostMapping("/auth/callback")
    public ResponseEntity<TokenDto> callback(
            @RequestBody Map<String, String> requestBody) {
        return ResponseEntity.ok(authService.callback(requestBody));
    }

    @GetMapping("/auth/check-email")
    public ResponseEntity<EmailCheckResponseDto> checkEmail(
            @RequestParam("email") String email) {
        return ResponseEntity.ok(authService.checkEmail(email));
    }

    @PostMapping("/exchange-token")
    public ResponseEntity<TokenDto> exchangeToken(
            @RequestBody TokenDto googleTokenDto) {
        return ResponseEntity.ok(authService.exchangeToken(googleTokenDto));
    }

}
