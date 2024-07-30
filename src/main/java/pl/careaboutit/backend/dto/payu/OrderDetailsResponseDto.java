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
public class OrderDetailsResponseDto {

    private List<OrderDto> orders;
    private OrderStatusDto status;
    private List<PropertiesDto> properties;

}
