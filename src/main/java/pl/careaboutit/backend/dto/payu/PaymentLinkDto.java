package pl.careaboutit.backend.dto.payu;

public record PaymentLinkDto(

        String value,
        String name,
        String brandImageUrl,
        String status,
        int minAmount,
        int maxAmount

) {
}
