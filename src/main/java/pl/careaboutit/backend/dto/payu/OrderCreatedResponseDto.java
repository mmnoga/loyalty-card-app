package pl.careaboutit.backend.dto.payu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedResponseDto {

    private UUID uuid;
    private StatusDto status;
    private String redirectUri;
    private String orderId;
    private String extOrderId;

}
