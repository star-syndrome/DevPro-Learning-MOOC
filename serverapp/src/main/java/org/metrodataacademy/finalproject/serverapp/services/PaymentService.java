package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddPaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdatePaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.PaymentResponse;

import java.util.List;

public interface PaymentService {

    List<PaymentResponse> getAllPayment();

    PaymentResponse getById(Integer id);

    PaymentResponse addPayment(AddPaymentRequest addPaymentRequest);

    PaymentResponse updatePayment(Integer id, UpdatePaymentRequest updatePaymentRequest);

    PaymentResponse deletePayment(Integer id);
}