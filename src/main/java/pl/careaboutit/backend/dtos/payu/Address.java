package pl.careaboutit.backend.dtos.payu;

public record Address(

        String street,
        String houseNumber,
        String apartmentNumber,
        String postalCode,
        String city,
        String country

) {
}
