package pl.careaboutit.backend.service.oauth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import pl.careaboutit.backend.dto.oauth.EmailCheckResponseDto;
import pl.careaboutit.backend.dto.oauth.TokenDto;
import pl.careaboutit.backend.dto.oauth.UrlDto;
import pl.careaboutit.backend.exception.TechnicalException;
import pl.careaboutit.backend.service.user.UserService;

import java.util.Map;

public interface OAuthService {

    /**
     * Generates a URL for the Google OAuth 2.0 authorization endpoint.
     * <p>
     * This method constructs the authorization URL with the specified client ID,
     * redirect URI, and requested scopes (email, profile, and openid). It uses
     * {@link GoogleAuthorizationCodeRequestUrl} to build the URL.
     * </p>
     *
     * @return an {@link UrlDto} object containing the constructed authorization URL.
     */
    UrlDto getAuthUrl();

    /**
     * Handles the callback from Google OAuth 2.0 authorization process.
     * <p>
     * This method exchanges the authorization code provided by Google for an access
     * token and ID token. It uses the {@link GoogleAuthorizationCodeTokenRequest} to
     * perform the exchange, utilizing the client ID, client secret, and redirect URI
     * previously set up for the application.
     * </p>
     *
     * @param requestBody a map containing the request parameters, including the "code"
     *                    parameter provided by Google as part of the authorization response.
     * @return a {@link TokenDto} containing the access token and the email address extracted
     * from the ID token.
     * @throws TechnicalException if there is an issue during the token exchange process,
     *                            such as an invalid code or network error, resulting in
     *                            an unauthorized status.
     */
    TokenDto callback(Map<String, String> requestBody);

    /**
     * Checks if an email address is already associated with an existing user in the system.
     * <p>
     * This method calls the {@link UserService#existsByEmail(String)} method to determine
     * if the specified email is registered. It returns an {@link EmailCheckResponseDto}
     * containing the result of this check.
     * </p>
     *
     * @param email the email address to check for existence.
     * @return an {@link EmailCheckResponseDto} containing a boolean flag indicating whether
     * the email exists in the system.
     */
    EmailCheckResponseDto checkEmail(String email);

    /**
     * Exchanges a Google ID token for a JWT token after verifying the Google token.
     * <p>
     * This method verifies the provided Google ID token and, if valid, generates a JWT token
     * for the user identified by the Google token. The method will throw a {@link TechnicalException}
     * in case of invalid tokens or other errors during the process.
     * </p>
     *
     * @param googleTokenDto the {@link TokenDto} containing the Google ID token to be verified.
     * @return a {@link TokenDto} containing the generated JWT token and the associated email.
     * @throws TechnicalException if the Google token is invalid, verification fails, or
     *                            there is an internal error during token processing.
     */
    TokenDto exchangeToken(TokenDto googleTokenDto);

}
