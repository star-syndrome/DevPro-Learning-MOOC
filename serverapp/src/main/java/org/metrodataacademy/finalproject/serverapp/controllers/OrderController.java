package org.metrodataacademy.finalproject.serverapp.controllers;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.OrderRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.OrderResponse;
import org.metrodataacademy.finalproject.serverapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(
            path = "/getOrderDetailsCourse/{title}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<OrderDetailsResponse> getOrderDetailsCourse(@PathVariable String title) {
        return ResponseEntity.ok()
                .body(orderService.getOrderDetailsCourse(title));
    }

    @PostMapping(
            path = "/createOrder",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('CREATE_USER')")
    public ResponseEntity<OrderResponse> orderCourse(@Validated @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok()
                .body(orderService.orderCourse(orderRequest));
    }

    @GetMapping(
            path = "/paymentHistoryForAdmin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('READ_ADMIN')")
    public ResponseEntity<List<OrderResponse>> getPaymentHistoriesForAdmin() {
        return ResponseEntity.ok()
                .body(orderService.getPaymentHistory());
    }
}