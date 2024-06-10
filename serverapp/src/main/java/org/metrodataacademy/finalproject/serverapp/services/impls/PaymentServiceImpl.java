package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddPaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdatePaymentRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.PaymentResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Payment;
import org.metrodataacademy.finalproject.serverapp.repositories.PaymentRepository;
import org.metrodataacademy.finalproject.serverapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse> getAllPayment() {
        log.info("Getting list of payments");
        return paymentRepository.findAll().stream()
                .map(this::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse getById(Integer id) {
        log.info("Try to get data payment with id {}", id);
        return paymentRepository.findById(id)
                .map(this::mapToPaymentResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!"));
    }

    @Override
    public PaymentResponse addPayment(AddPaymentRequest addPaymentRequest) {
        try {
            log.info("Process of adding new payment {}", addPaymentRequest.getName());
            if (paymentRepository.existsByName(addPaymentRequest.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment already exists!");
            }

            Payment payment = new Payment();
            payment.setName(addPaymentRequest.getName());

            paymentRepository.save(payment);

            log.info("Adding new payment was successful, new payment: {}", payment.getName());
            return mapToPaymentResponse(payment);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public PaymentResponse updatePayment(Integer id, UpdatePaymentRequest updatePaymentRequest) {
        try {
            log.info("Try to update payment data with name {}", updatePaymentRequest.getName());
            Payment payment = paymentRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!"));

            if (paymentRepository.existsByName(updatePaymentRequest.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment name already exists!");
            }

            payment.setName(updatePaymentRequest.getName());
            paymentRepository.save(payment);

            log.info("Updating payment {} was successful!", updatePaymentRequest.getName());
            return mapToPaymentResponse(payment);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public PaymentResponse deletePayment(Integer id) {
        try {
            log.info("Try to delete payment data with id {}", id);
            Payment payment = paymentRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found!"));

            paymentRepository.delete(payment);

            log.info("Deleting payment with id: {} was successful!", id);
            return mapToPaymentResponse(payment);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    private PaymentResponse mapToPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .name(payment.getName())
                .build();
    }
}