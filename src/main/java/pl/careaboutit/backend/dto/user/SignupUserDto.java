package pl.careaboutit.backend.dto.user;

import pl.careaboutit.backend.model.AuthProvider;

import java.util.Optional;
import java.util.Set;

public record SignupUserDto(

        String email,
        String firstName,
        String lastName,
        String password,
        Optional<String> phone,
        AuthProvider authProvider,
        Set<String> roles

) {
}
