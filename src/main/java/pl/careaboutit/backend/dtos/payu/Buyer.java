package pl.careaboutit.backend.dtos.payu;

public record Buyer(

        String email,
        String phone,
        String firstName,
        String lastName,
        String language

) {
}
