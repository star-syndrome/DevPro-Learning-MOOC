package org.metrodataacademy.finalproject.clientapp.services;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddPaymentRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdatePaymentRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.PaymentResponse;

public interface PaymentService {

    List<PaymentResponse> getAllPayment();

    PaymentResponse getById(Integer id);

    PaymentResponse addPayment(AddPaymentRequest addPaymentRequest);

    PaymentResponse updatePayment(Integer id, UpdatePaymentRequest updatePaymentRequest);

    PaymentResponse deletePayment(Integer id);
}