package org.metrodataacademy.finalproject.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/dashboard")
    public String dashboardView(){
        return "pages/adminpage/dashboard";
    }

    @GetMapping("/course")
    public String kelolaKelasView(){
        return "pages/adminpage/course";
    }

    @GetMapping("/category")
    public String kategoriKelasView() {
        return "pages/adminpage/category";
    }

    @GetMapping("/payment-method")
    public String metodePembayaranView() {
        return "pages/adminpage/payment-method";
    }
}