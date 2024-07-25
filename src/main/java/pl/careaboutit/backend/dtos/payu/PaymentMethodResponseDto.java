package pl.careaboutit.backend.dtos.payu;

import java.util.List;

public record PaymentMethodResponseDto(

        List<PaymentLinkDto> payByLinks

) {
}
