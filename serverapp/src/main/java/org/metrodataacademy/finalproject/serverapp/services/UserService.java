package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.ChangePasswordRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateUserProfileRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.UserResponse;

public interface UserService {

    UserResponse getUserProfile();

    UserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest);

    UserResponse changePasswordUser(ChangePasswordRequest changePasswordRequest);

    Long countAllUsers();
}