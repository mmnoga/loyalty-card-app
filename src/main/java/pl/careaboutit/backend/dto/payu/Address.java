package pl.careaboutit.backend.dto.payu;

public record Address(

        String street,
        String houseNumber,
        String apartmentNumber,
        String postalCode,
        String city,
        String country

) {
}
