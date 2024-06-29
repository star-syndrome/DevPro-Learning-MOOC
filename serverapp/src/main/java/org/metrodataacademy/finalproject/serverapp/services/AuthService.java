package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.ForgotPasswordRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.LoginRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.RegistrationRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.LoginResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.RegistrationResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.UserResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    UserResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
}