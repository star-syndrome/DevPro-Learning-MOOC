package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.OrderRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderResponse;

import java.util.List;

import javax.mail.MessagingException;

public interface OrderService {

    OrderDetailsResponse getOrderDetailsCourse(String title);

    OrderResponse orderCourse(OrderRequest orderRequest) throws MessagingException;

    List<OrderResponse> getPaymentHistory();
}