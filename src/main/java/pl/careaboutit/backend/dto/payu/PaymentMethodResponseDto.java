package pl.careaboutit.backend.dto.payu;

import java.util.List;

public record PaymentMethodResponseDto(

        List<PaymentLinkDto> payByLinks

) {
}
