package org.metrodataacademy.finalproject.clientapp.controllers.rest;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.ChangePasswordRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateUserProfileRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.UserResponse;
import org.metrodataacademy.finalproject.clientapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/profile")
public class RestProfileController {

    @Autowired
    private UserService userService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok()
                .body(userService.getUserProfile());
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
        return ResponseEntity.ok()
                .body(userService.updateUserProfile(updateUserProfileRequest));
    }

    @PutMapping(
            path = "/change-password",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok()
                .body(userService.changePasswordUser(changePasswordRequest));
    }
}