package pl.careaboutit.backend.dto.user;

public record UserResponseDto(

        String email,
        String firstName,
        String lastName,
        String phone,
        String role

) {
}
