package org.metrodataacademy.finalproject.clientapp.controllers;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.LoginRequest;
import org.metrodataacademy.finalproject.clientapp.services.AuthService;
import org.metrodataacademy.finalproject.clientapp.utils.AuthSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping(
            path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String loginView(LoginRequest loginRequest) {
        Authentication authentication = AuthSessionUtil.getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }
        return "redirect:/home";
    }

    @PostMapping(
        path = "/login",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String login(LoginRequest loginRequest) {
        if (!authService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        return "redirect:/home";
    }

    @GetMapping(
        path = "/registration",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String registrationView() {
        return "auth/registration";
    }
}