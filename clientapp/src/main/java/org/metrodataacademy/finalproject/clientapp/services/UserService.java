package org.metrodataacademy.finalproject.clientapp.services;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.ChangePasswordRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateUserProfileRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.UserResponse;

public interface UserService {

    UserResponse getUserProfile();

    UserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest);

    UserResponse changePasswordUser(ChangePasswordRequest changePasswordRequest);

    Long countAllUsers();
}