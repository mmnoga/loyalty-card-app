package pl.careaboutit.backend.dto.payu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto{

        private String notifyUrl;
        private String customerIp;
        private String merchantPosId;
        private String description;
        private String currencyCode;
        private String totalAmount;
        private String continueUrl;
        private String extOrderId;
        private Buyer buyer;
        private List<Product> products;

}