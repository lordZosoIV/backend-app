package com.flatrcok.order.service.external.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApiService {
    private final RestTemplate restTemplate;
    @Value("${api.product.url}")
    private String baseUrl;

    public List<Product> filter(List<Long> productIds) {

        String productsFilterEndpoint = "product/filter";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl.concat(productsFilterEndpoint))
                .queryParam("ids", productIds);

        return restTemplate.exchange(
                uriBuilder.build().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }).getBody();
    }

}
