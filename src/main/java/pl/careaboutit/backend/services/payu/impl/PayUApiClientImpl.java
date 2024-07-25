package pl.careaboutit.backend.services.payu.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.careaboutit.backend.dtos.payu.OrderRequestDto;
import pl.careaboutit.backend.dtos.payu.OrderResponseDto;
import pl.careaboutit.backend.dtos.payu.PaymentMethodResponseDto;
import pl.careaboutit.backend.services.payu.PayUApiClient;

@Service
@RequiredArgsConstructor
public class PayUApiClientImpl implements PayUApiClient {

    private static final String BASE_URL = "https://secure.snd.payu.com";
    private static final String PAYMENT_METHODS_URL = "/api/v2_1/paymethods";
    private static final String ORDER_CREATE_URL = "/api/v2_1/orders";

    @Qualifier("payURestTemplate")
    private final RestTemplate restTemplate;

    @Override
    public PaymentMethodResponseDto getPaymentMethods() {
        String paymentMethodsUrl = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .path(PAYMENT_METHODS_URL)
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-cache");

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<PaymentMethodResponseDto> responseEntity = restTemplate.exchange(
                paymentMethodsUrl,
                HttpMethod.GET,
                request,
                PaymentMethodResponseDto.class);

        return responseEntity.getBody();
    }

    @Override
    public OrderResponseDto submitOrder(OrderRequestDto order) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String submitOrderUrl = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .path(ORDER_CREATE_URL)
                .build()
                .toUriString();

        HttpEntity<OrderRequestDto> request =
                new HttpEntity<>(order, headers);

        ResponseEntity<OrderResponseDto> responseEntity = restTemplate.exchange(
                submitOrderUrl,
                HttpMethod.POST,
                request,
                OrderResponseDto.class);

        return responseEntity.getBody();
    }

}
