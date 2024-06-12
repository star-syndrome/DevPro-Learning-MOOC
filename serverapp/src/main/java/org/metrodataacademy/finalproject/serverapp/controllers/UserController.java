package org.metrodataacademy.finalproject.serverapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.ChangePasswordRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateUserProfileRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.UserResponse;
import org.metrodataacademy.finalproject.serverapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@PreAuthorize(value = "hasRole('USER')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(
            path = "/get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for user to get profile")
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok()
                .body(userService.getUserProfile());
    }

    @PutMapping(
            path = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for user to update profile")
    @PreAuthorize(value = "hasAuthority('UPDATE_USER')")
    public ResponseEntity<UserResponse> updateUser(@Validated @RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
        return ResponseEntity.ok()
                .body(userService.updateUserProfile(updateUserProfileRequest));
    }

    @PutMapping(
            path = "/changePassword",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for user to change password")
    @PreAuthorize(value = "hasAuthority('UPDATE_USER')")
    public ResponseEntity<UserResponse> changePassword(@Validated @RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok()
                .body(userService.changePasswordUser(changePasswordRequest));
    }
}