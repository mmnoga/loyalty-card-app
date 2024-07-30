package pl.careaboutit.backend.dto.user;

import pl.careaboutit.backend.model.AuthProvider;

import java.util.Set;

public record UserResponseDto(

        String email,
        String firstName,
        String lastName,
        AuthProvider authProvider,
        Set<String> roles,
        String token

) {
}
