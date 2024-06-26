package org.metrodataacademy.finalproject.clientapp.services;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.OrderRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.OrderDetailsResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.OrderResponse;

public interface OrderService {

    List<OrderResponse> getPaymentHistory();

    OrderDetailsResponse getOrderDetailsCourse(String title);

    OrderResponse createOrder(OrderRequest orderRequest);
}