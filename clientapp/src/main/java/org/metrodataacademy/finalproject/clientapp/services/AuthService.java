package org.metrodataacademy.finalproject.clientapp.services;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.LoginRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.RegistrationRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.RegistrationResponse;

public interface AuthService {

    Boolean login(LoginRequest loginRequest);

    RegistrationResponse registration(RegistrationRequest registrationRequest);
}