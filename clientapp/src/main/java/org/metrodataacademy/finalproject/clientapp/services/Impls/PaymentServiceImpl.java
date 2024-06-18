package org.metrodataacademy.finalproject.clientapp.services.Impls;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddPaymentRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdatePaymentRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.PaymentResponse;
import org.metrodataacademy.finalproject.clientapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api";

    @Override
    public List<PaymentResponse> getAllPayment() {
        return restTemplate
            .exchange(
                url + "/payment",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PaymentResponse>>() {}
            ).getBody();
    }

    @Override
    public PaymentResponse getById(Integer id) {
        return restTemplate
            .exchange(
                url + "/payment/" + id,
                HttpMethod.GET,
                null,
                PaymentResponse.class
            ).getBody();
    }

    @Override
    public PaymentResponse addPayment(AddPaymentRequest addPaymentRequest) {
        return restTemplate
            .exchange(
                url + "/admin/payment",
                HttpMethod.POST,
                new HttpEntity<AddPaymentRequest>(addPaymentRequest),
                PaymentResponse.class
            ).getBody();
    }

    @Override
    public PaymentResponse updatePayment(Integer id, UpdatePaymentRequest updatePaymentRequest) {
        return restTemplate
            .exchange(
                url + "/admin/payment/" + id,
                HttpMethod.PUT,
                new HttpEntity<UpdatePaymentRequest>(updatePaymentRequest),
                PaymentResponse.class
            ).getBody();
    }

    @Override
    public PaymentResponse deletePayment(Integer id) {
        return restTemplate
            .exchange(
                url + "/admin/payment/" + id,
                HttpMethod.DELETE,
                null,
                PaymentResponse.class
            ).getBody();
    }   
}