package org.metrodataacademy.finalproject.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String landingPage() {
        return "index";
    }

    @GetMapping(
        path = "/home"
    )
    public String dashboard(Model model) {
        model.addAttribute("isActive", "home");
        return "dashboard";
    }
    
    @GetMapping(
        path = "/payment-success"
    )
    public String paymentSuccess() {
        return "pages/user/payment-success";
    }


}