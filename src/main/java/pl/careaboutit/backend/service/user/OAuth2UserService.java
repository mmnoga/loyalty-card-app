package pl.careaboutit.backend.service.user;

import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.exception.BusinessException;

public interface OAuth2UserService {

    /**
     * Registers a new user using OAuth2 authentication data.
     *
     * @param oauth2User the OAuth2 user containing authentication data.
     * @return a {@link UserResponseDto} representing the registered user.
     * @throws BusinessException if a user with the provided email already exists.
     */
    UserResponseDto signUpUser(OAuth2User oauth2User);

    /**
     * Retrieves an existing user based on OAuth2 authentication data.
     *
     * @param oAuth2User the OAuth2 user containing authentication data.
     * @return a {@link UserResponseDto} representing the found user.
     * @throws BusinessException if no user with the provided email is found.
     */
    UserResponseDto getUser(OAuth2User oAuth2User);

    /**
     * Checks if a user with the given email address exists.
     *
     * @param email the email address to check for user existence.
     * @return {@code true} if a user with the given email exists, {@code false} otherwise.
     */
    boolean userExists(String email);

}
