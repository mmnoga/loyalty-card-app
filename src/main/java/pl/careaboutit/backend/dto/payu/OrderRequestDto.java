package pl.careaboutit.backend.dto.payu;

import java.util.List;

public record OrderRequestDto(

        String notifyUrl,
        String customerIp,
        String merchantPosId,
        String description,
        String currencyCode,
        String totalAmount,
        String continueUrl,
        String extOrderId,
        Buyer buyer,
        List<Product> products

) {
}
