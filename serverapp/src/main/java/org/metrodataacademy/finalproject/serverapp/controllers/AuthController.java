package org.metrodataacademy.finalproject.serverapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.LoginRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.RegistrationRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CategoryResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.LoginResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.RegistrationResponse;
import org.metrodataacademy.finalproject.serverapp.services.AuthService;
import org.metrodataacademy.finalproject.serverapp.services.CategoryService;
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

    @Autowired
    private CategoryService categoryService;

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
            path = "/course/before-login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get all course before login (landing page)")
    public ResponseEntity<List<CourseResponse>> getAllCourseBeforeLogin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseBeforeLogin());
    }

    @GetMapping(
            path = "/category",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get all category (landing page)")
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }
}