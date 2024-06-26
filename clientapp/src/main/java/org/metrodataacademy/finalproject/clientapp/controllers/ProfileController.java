package org.metrodataacademy.finalproject.clientapp.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    
    @GetMapping(
        path = "/profile",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getProfileView(Model model) {
        model.addAttribute("isActive", "profile");
        return "pages/user/account-details";
    }  

    @GetMapping(
        path = "/change-password",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getChangePasswordView(Model model) {
        model.addAttribute("isActive", "profile");
        return "pages/user/update-password";
    }
}