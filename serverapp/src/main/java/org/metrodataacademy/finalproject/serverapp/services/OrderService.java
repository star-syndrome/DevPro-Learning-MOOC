package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.OrderRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderDetailsResponse getOrderDetailsCourse(String title);

    OrderResponse orderCourse(OrderRequest orderRequest);

    List<OrderResponse> getPaymentHistory();
}