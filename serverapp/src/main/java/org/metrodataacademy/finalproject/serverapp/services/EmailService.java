package org.metrodataacademy.finalproject.serverapp.services;

import javax.mail.MessagingException;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.ReceiptRequest;

public interface EmailService {

    void sendReceipt(ReceiptRequest receiptRequest) throws MessagingException;
}