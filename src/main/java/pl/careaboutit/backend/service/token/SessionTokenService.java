package pl.careaboutit.backend.service.token;

import pl.careaboutit.backend.dto.MessageResponseDto;
import pl.careaboutit.backend.exception.TechnicalException;
import pl.careaboutit.backend.model.SessionToken;

import java.util.Date;

public interface SessionTokenService {

    /**
     * Saves a new session token in the repository.
     * <p>
     * This method creates a new {@link SessionToken} object with the provided details and stores it in the repository.
     * </p>
     *
     * @param token      the token string to be saved.
     * @param email      the email associated with the token.
     * @param issuedAt   the date and time when the token was issued.
     * @param expiration the date and time when the token will expire.
     */
    void saveToken(String token, String email, Date issuedAt, Date expiration);

    /**
     * Deletes a session token from the repository.
     * <p>
     * This method searches for a session token by its value. If the token is found, it is deleted
     * from the repository. If the token is not found, a {@link TechnicalException} is thrown.
     * </p>
     *
     * @param token the token string to be deleted.
     * @return a {@link MessageResponseDto} containing a message indicating the successful deletion of the token.
     * @throws TechnicalException if the token is not found in the repository.
     */
    MessageResponseDto deleteToken(String token);

    /**
     * Checks if a session token exists in the repository.
     * <p>
     * This method verifies the existence of a session token by its value.
     * It returns {@code true} if the token exists, and {@code false} otherwise.
     * </p>
     *
     * @param token the token string to be checked.
     * @return {@code true} if the token exists in the repository, {@code false} otherwise.
     */
    boolean isTokenValid(String token);

}
