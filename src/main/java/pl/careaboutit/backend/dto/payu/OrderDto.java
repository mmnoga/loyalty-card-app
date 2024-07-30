package pl.careaboutit.backend.dto.payu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("extOrderId")
    private String extOrderId;

    @JsonProperty("orderCreateDate")
    private String orderCreateDate;

    @JsonProperty("notifyUrl")
    private String notifyUrl;

    @JsonProperty("customerIp")
    private String customerIp;

    @JsonProperty("merchantPosId")
    private String merchantPosId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("currencyCode")
    private String currencyCode;

    @JsonProperty("totalAmount")
    private String totalAmount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("products")
    private List<ProductDto> products;

}
