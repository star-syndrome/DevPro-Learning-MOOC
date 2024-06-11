package org.metrodataacademy.finalproject.serverapp.controllers;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.LoginRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.RegistrationRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.LoginResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.RegistrationResponse;
import org.metrodataacademy.finalproject.serverapp.services.AuthService;
import org.metrodataacademy.finalproject.serverapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8300", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CourseService courseService;

    @PostMapping(
            path = "/auth/registration",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RegistrationResponse> registration(@Validated @RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok().body(authService.registration(registrationRequest));
    }

    @PostMapping(
            path = "/auth/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @GetMapping(
            path = "/course/getAllCourseBeforeLogin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseResponse>> getAllCourseBeforeLogin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseBeforeLogin());
    }
}