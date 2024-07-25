package pl.careaboutit.backend.service.payu.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.careaboutit.backend.dto.payu.OrderRequestDto;
import pl.careaboutit.backend.dto.payu.OrderResponseDto;
import pl.careaboutit.backend.dto.payu.PaymentMethodResponseDto;
import pl.careaboutit.backend.service.payu.PayUApiClient;
import pl.careaboutit.backend.service.payu.PayUService;

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
