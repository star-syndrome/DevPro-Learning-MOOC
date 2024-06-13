package org.metrodataacademy.finalproject.clientapp.services;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.LoginRequest;

public interface AuthService {

    Boolean login(LoginRequest loginRequest);
}