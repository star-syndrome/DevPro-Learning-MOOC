package org.metrodataacademy.finalproject.clientapp.controllers.rest;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddPaymentRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdatePaymentRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.PaymentResponse;
import org.metrodataacademy.finalproject.clientapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @GetMapping(
        path = "/payment",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PaymentResponse>> getAllPayment() {
        return ResponseEntity.ok().body(paymentService.getAllPayment());
    }

    @GetMapping(
        path = "/payment/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(paymentService.getById(id));
    }

    @PostMapping(
        path = "/admin/payment",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PaymentResponse> addPayment(@RequestBody AddPaymentRequest addPaymentRequest) {
        return ResponseEntity.ok().body(paymentService.addPayment(addPaymentRequest));
    }

    @PutMapping(
        path = "/admin/payment/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Integer id, @RequestBody UpdatePaymentRequest updatePaymentRequest) {
        return ResponseEntity.ok().body(paymentService.updatePayment(id, updatePaymentRequest));
    }

    @DeleteMapping(
        path = "/admin/payment/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PaymentResponse> deletePayment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(paymentService.deletePayment(id));
    }
}