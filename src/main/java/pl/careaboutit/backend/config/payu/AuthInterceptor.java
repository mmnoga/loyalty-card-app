package pl.careaboutit.backend.config.payu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.careaboutit.backend.dto.payu.PayUAuthResponseDto;

import java.io.IOException;

public class AuthInterceptor implements ClientHttpRequestInterceptor {

    private static final String BASE_URL = "https://secure.snd.payu.com";
    private static final String AUTH_URL = "/pl/standard/user/oauth/authorize";

    private final String clientId;
    private final String clientSecret;
    private String accessToken;
    private long tokenExpirationTime;
    private PayUAuthResponseDto lastAuthResponse;

    public AuthInterceptor(
            @Value("${payu.client-id}") String clientId,
            @Value("${payu.client-secret}") String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessToken = "";
        this.tokenExpirationTime = 0;
        this.lastAuthResponse = null;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        if (!isTokenValid()) {
            refreshAccessToken();
        }

        HttpHeaders headers = request.getHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        return execution.execute(request, body);
    }

    private boolean isTokenValid() {
        return !accessToken.isEmpty() && System.currentTimeMillis() < tokenExpirationTime;
    }

    private void refreshAccessToken() {
        String newAccessToken = getAccessToken(clientId, clientSecret);
        if (!newAccessToken.isEmpty()) {
            accessToken = newAccessToken;
            tokenExpirationTime = System.currentTimeMillis() + getExpiresIn();
        }
    }

    private long getExpiresIn() {
        if (lastAuthResponse != null) {
            long expiresIn = lastAuthResponse.getExpiresIn();
            return expiresIn > 10 ? expiresIn - 10 : expiresIn;
        } else {
            return 3600;
        }
    }

    private String getAccessToken(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String authUrl = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .path(AUTH_URL)
                .build()
                .toUriString();

        String requestBody = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
                clientId, clientSecret);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<PayUAuthResponseDto> responseEntity = new RestTemplate().exchange(
                authUrl,
                HttpMethod.POST,
                request,
                PayUAuthResponseDto.class);

        lastAuthResponse = responseEntity.getBody();

        return responseEntity
                .getBody() != null ?
                responseEntity.getBody().getAccessToken() :
                "";
    }

}
