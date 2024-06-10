package org.metrodataacademy.finalproject.serverapp.controllers;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddPaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdatePaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.PaymentResponse;
import org.metrodataacademy.finalproject.serverapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(
            path = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<List<PaymentResponse>> getAllPayment() {
        return ResponseEntity.ok().body(paymentService.getAllPayment());
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<PaymentResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(paymentService.getById(id));
    }

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<PaymentResponse> addPayment(@Validated @RequestBody AddPaymentRequest addPaymentRequest) {
        return ResponseEntity.ok().body(paymentService.addPayment(addPaymentRequest));
    }

    @PutMapping(
            path = "/update/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<PaymentResponse> updatePayment(
            @PathVariable Integer id, @Validated @RequestBody UpdatePaymentRequest updatePaymentRequest) {
        return ResponseEntity.ok().body(paymentService.updatePayment(id, updatePaymentRequest));
    }

    @DeleteMapping(
            path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<PaymentResponse> deletePayment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(paymentService.deletePayment(id));
    }
}