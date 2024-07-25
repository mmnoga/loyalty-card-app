package pl.careaboutit.backend.dtos.payu;

import java.time.Instant;
import java.util.UUID;

public record OrderResponseDto(

        UUID orderUid,
        String deliveryMethod,
        String customerEmail,
        Address deliveryAddress,
        String paymentMethod,
        Instant orderDate,
        String customerPhone

) {
}
