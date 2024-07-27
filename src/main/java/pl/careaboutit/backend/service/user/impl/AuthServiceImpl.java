package pl.careaboutit.backend.service.user.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.auth.GoogleTokenVerifier;
import pl.careaboutit.backend.auth.JwtTokenProvider;
import pl.careaboutit.backend.dto.AuthResponseDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.exception.BusinessException;
import pl.careaboutit.backend.service.user.AuthService;
import pl.careaboutit.backend.service.user.OAuth2UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OAuth2UserService oauth2UserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final GoogleTokenVerifier googleTokenVerifier;

    @Override
    public AuthResponseDto login(String googleToken) {
        GoogleIdToken.Payload payload = googleTokenVerifier.verify(googleToken);
        if (payload == null) {
            throw new BusinessException("Invalid Google token", HttpStatus.UNAUTHORIZED);
        }

        String email = payload.getEmail();
        OAuth2User oAuth2User = createOAuth2User(payload);
        UserResponseDto user;

        if (oauth2UserService.userExists(email)) {
            user = oauth2UserService.getUser(oAuth2User);
        } else {
            user = oauth2UserService.signUpUser(oAuth2User);
        }

        String jwtToken = jwtTokenProvider.generateToken(email);
        return new AuthResponseDto(jwtToken, user);
    }

    private OAuth2User createOAuth2User(GoogleIdToken.Payload payload) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("sub", payload.getSubject());
        attributes.put("email", payload.getEmail());
        attributes.put("given_name", payload.get("given_name"));
        attributes.put("family_name", payload.get("family_name"));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "email"
        );
    }
}
