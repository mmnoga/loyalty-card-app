package pl.careaboutit.backend.dto.payu;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder
public class OrderPaymentResponseDTO {

    private UUID orderUid;
    private String deliveryMethod;
    private String customerEmail;
    private String paymentMethod;
    private Instant orderDate;
    private String customerPhone;

}
