package pl.careaboutit.backend.dto.loyalty;

public record LoyaltyCardResponseDto(

        String cardNumber,
        String status,
        int points

) {
}
