package pl.careaboutit.backend.dto.user;

public record SignupUserDto(

        String email,
        String firstName,
        String lastName,
        String password,
        String phone

) {
}
