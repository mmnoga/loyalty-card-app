package pl.careaboutit.backend.dto.loyalty;

public record UpdateRequestDto(

        String cardNumber,
        int points,
        PointOperation operation

) {
}