package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.EmailRequest;

public interface EmailService {

    void sendEmail(EmailRequest emailRequest);
}