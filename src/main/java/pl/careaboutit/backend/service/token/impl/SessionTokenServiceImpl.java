package pl.careaboutit.backend.service.token.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.dto.MessageResponseDto;
import pl.careaboutit.backend.exception.TechnicalException;
import pl.careaboutit.backend.model.SessionToken;
import pl.careaboutit.backend.repository.SessionTokenRepository;
import pl.careaboutit.backend.service.token.SessionTokenService;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class SessionTokenServiceImpl implements SessionTokenService {

    private final SessionTokenRepository sessionTokenRepository;

    @Override
    public void saveToken(String token, String email, Date issuedAt, Date expiration) {
        SessionToken sessionToken = new SessionToken();
        sessionToken.setToken(token);
        sessionToken.setUserEmail(email);
        sessionToken.setIssuedAt(issuedAt);
        sessionToken.setExpiration(expiration);
        sessionTokenRepository.save(sessionToken);
    }

    @Override
    public MessageResponseDto deleteToken(String token) {
        SessionToken sessionToken = sessionTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new TechnicalException(
                                "Token " + token + " not found",
                                HttpStatus.NOT_FOUND));

        sessionTokenRepository.delete(sessionToken);

        return new MessageResponseDto("Token " + token + " deleted successfully");
    }

    @Override
    public boolean isTokenValid(String token) {
        return sessionTokenRepository.existsByToken(token);
    }

}
