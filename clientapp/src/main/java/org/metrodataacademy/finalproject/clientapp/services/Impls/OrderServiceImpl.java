package org.metrodataacademy.finalproject.clientapp.services.Impls;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.OrderResponse;
import org.metrodataacademy.finalproject.clientapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api";

    @Override
    public List<OrderResponse> getPaymentHistory() {
        return restTemplate
            .exchange(
                url + "/admin/order/payment-history",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderResponse>>() {}
            ).getBody();
    }
}