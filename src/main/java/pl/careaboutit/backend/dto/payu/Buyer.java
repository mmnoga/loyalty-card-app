package pl.careaboutit.backend.dto.payu;

public record Buyer(

        String email,
        String phone,
        String firstName,
        String lastName,
        String language

) {
}
