package org.metrodataacademy.finalproject.serverapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(
            path = "/order/{title}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for user to get order details")
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<OrderDetailsResponse> getOrderDetailsCourse(@PathVariable String title) {
        return ResponseEntity.ok()
                .body(orderService.getOrderDetailsCourse(title));
    }

    @PostMapping(
            path = "/order",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for user to order course")
    @PreAuthorize(value = "hasAuthority('CREATE_USER')")
    public ResponseEntity<OrderResponse> orderCourse(@Validated @RequestBody OrderRequest orderRequest) throws MessagingException {
        return ResponseEntity.ok()
                .body(orderService.orderCourse(orderRequest));
    }

    @GetMapping(
            path = "/admin/order/payment-history",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to see payment history")
    @PreAuthorize(value = "hasAuthority('READ_ADMIN')")
    public ResponseEntity<List<OrderResponse>> getPaymentHistoriesForAdmin() {
        return ResponseEntity.ok()
                .body(orderService.getPaymentHistory());
    }
}