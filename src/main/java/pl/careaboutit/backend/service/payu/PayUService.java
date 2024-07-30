package pl.careaboutit.backend.service.payu;

import pl.careaboutit.backend.dto.payu.OrderCreatedResponseDto;
import pl.careaboutit.backend.dto.payu.OrderDetailsResponseDto;
import pl.careaboutit.backend.dto.payu.OrderRequestDto;
import pl.careaboutit.backend.dto.payu.PaymentMethodResponseDto;

public interface PayUService {

    /**
     * Retrieves payment methods.
     *
     * @return The response containing payment methods.
     * Throws Payu system code if there is an exception during the retrieval process.
     */
    PaymentMethodResponseDto getPaymentMethods();

    /**
     * Retrieves answer for request the creation new order in PayU system.
     *
     * @param order The request DTO object with data for new Order which is sending to PayU.
     * @return The response contains the state of new Order in DTO object.
     */
    OrderCreatedResponseDto submitOrder(OrderRequestDto order);

    OrderDetailsResponseDto getOrderStatus(String orderId);
}
