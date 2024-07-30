package pl.careaboutit.backend.controller.payu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.dto.payu.OrderCreatedResponseDto;
import pl.careaboutit.backend.dto.payu.OrderDetailsResponseDto;
import pl.careaboutit.backend.dto.payu.OrderRequestDto;
import pl.careaboutit.backend.dto.payu.PaymentMethodResponseDto;
import pl.careaboutit.backend.service.payu.PayUService;

@RestController
@RequestMapping("/payu")
@RequiredArgsConstructor
public class PayUController {

    private final PayUService payUService;

    @GetMapping("/payment-methods")
    public ResponseEntity<PaymentMethodResponseDto> getPaymentMethods() {
        return ResponseEntity.ok(payUService
                .getPaymentMethods());
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderCreatedResponseDto> submitOrder(
            @RequestBody OrderRequestDto order) {
        return ResponseEntity.ok(payUService
                .submitOrder(order));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailsResponseDto> getOrderStatus(
            @PathVariable String orderId) {
        return ResponseEntity.ok(payUService
                .getOrderStatus(orderId));
    }

}
