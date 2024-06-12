package org.metrodataacademy.finalproject.serverapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddPaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdatePaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.PaymentResponse;
import org.metrodataacademy.finalproject.serverapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(
            path = "/payment",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get all payment")
    @PreAuthorize(value = "hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    public ResponseEntity<List<PaymentResponse>> getAllPayment() {
        return ResponseEntity.ok().body(paymentService.getAllPayment());
    }

    @GetMapping(
            path = "/payment/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get payment by id")
    @PreAuthorize(value = "hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    public ResponseEntity<PaymentResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(paymentService.getById(id));
    }

    @PostMapping(
            path = "/admin/payment",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to create payment")
    @PreAuthorize(value = "hasAuthority('CREATE_ADMIN')")
    public ResponseEntity<PaymentResponse> addPayment(@Validated @RequestBody AddPaymentRequest addPaymentRequest) {
        return ResponseEntity.ok().body(paymentService.addPayment(addPaymentRequest));
    }

    @PutMapping(
            path = "/admin/payment/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to update payment")
    @PreAuthorize(value = "hasAuthority('UPDATE_ADMIN')")
    public ResponseEntity<PaymentResponse> updatePayment(
            @PathVariable Integer id, @Validated @RequestBody UpdatePaymentRequest updatePaymentRequest) {
        return ResponseEntity.ok().body(paymentService.updatePayment(id, updatePaymentRequest));
    }

    @DeleteMapping(
            path = "/admin/payment/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to delete payment")
    @PreAuthorize(value = "hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<PaymentResponse> deletePayment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(paymentService.deletePayment(id));
    }
}