package org.metrodataacademy.finalproject.clientapp.controllers.rest;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.OrderResponse;
import org.metrodataacademy.finalproject.clientapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping(
            path = "/admin/order/payment-history",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<OrderResponse>> getPaymentHistoriesForAdmin() {
        return ResponseEntity.ok().body(orderService.getPaymentHistory());
    }
}