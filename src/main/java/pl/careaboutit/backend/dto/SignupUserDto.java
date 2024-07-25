package pl.careaboutit.backend.dto;

public record SignupUserDto(

        String email,
        String firstName,
        String lastName,
        String password,
        String phone

) {
}
