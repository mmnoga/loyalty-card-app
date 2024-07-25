package pl.careaboutit.backend.services.payu.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.dtos.payu.OrderRequestDto;
import pl.careaboutit.backend.dtos.payu.OrderResponseDto;
import pl.careaboutit.backend.dtos.payu.PaymentMethodResponseDto;
import pl.careaboutit.backend.services.payu.PayUApiClient;
import pl.careaboutit.backend.services.payu.PayUService;

@Service
@RequiredArgsConstructor
public class PayUServiceImpl implements PayUService {

    private final PayUApiClient payUApiClient;

    @Override
    public PaymentMethodResponseDto getPaymentMethods() {
        return payUApiClient
                .getPaymentMethods();
    }

    @Override
    public OrderResponseDto submitOrder(OrderRequestDto order) {
        return payUApiClient
                .submitOrder(order);
    }

}
