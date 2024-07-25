package pl.careaboutit.backend.dto;

public record UserResponseDto(

        String email,
        String firstName,
        String lastName,
        String phone,
        String role

) {
}
