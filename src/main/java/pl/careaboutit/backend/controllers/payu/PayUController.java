package pl.careaboutit.backend.controllers.payu;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.careaboutit.backend.dtos.payu.OrderRequestDto;
import pl.careaboutit.backend.dtos.payu.OrderResponseDto;
import pl.careaboutit.backend.dtos.payu.PaymentMethodResponseDto;
import pl.careaboutit.backend.services.payu.PayUService;

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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<OrderResponseDto> submitOrder(
            @RequestBody OrderRequestDto order) {
        return ResponseEntity.ok(payUService.
                submitOrder(order));
    }

}
