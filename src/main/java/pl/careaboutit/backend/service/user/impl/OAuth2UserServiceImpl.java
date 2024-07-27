package pl.careaboutit.backend.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.dto.user.SignupUserDto;
import pl.careaboutit.backend.dto.user.UserResponseDto;
import pl.careaboutit.backend.exception.BusinessException;
import pl.careaboutit.backend.model.AuthProvider;
import pl.careaboutit.backend.service.user.OAuth2UserService;
import pl.careaboutit.backend.service.user.UserService;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl implements OAuth2UserService {

    private final UserService userService;

    @Override
    public UserResponseDto signUpUser(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String firstName = oauth2User.getAttribute("given_name");
        String lastName = oauth2User.getAttribute("family_name");
        Set<String> defaultRoles = Set.of("USER");

        SignupUserDto userDto = new SignupUserDto(
                email,
                firstName,
                lastName,
                generateRandomPassword(),
                Optional.empty(),
                AuthProvider.GOOGLE,
                defaultRoles
        );

        return userService.addUser(userDto);
    }

    @Override
    public UserResponseDto getUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");

        return userService.findUserByEmail(email);
    }

    @Override
    public boolean userExists(String email) {
        try {
            userService.findUserByEmail(email);
            return true;
        } catch (BusinessException e) {
            return false;
        }
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString();
    }

}
