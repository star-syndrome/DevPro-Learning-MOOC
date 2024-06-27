package org.metrodataacademy.finalproject.clientapp.controllers;

import org.metrodataacademy.finalproject.clientapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping(
        path = "/order/{title}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getOrderDetailsView(@PathVariable String title, Model model) {
        model.addAttribute("order", orderService.getOrderDetailsCourse(title));
        model.addAttribute("courseTitle", title);
        return "pages/user/payment-page";
    }
    
    @GetMapping(
        path = "/payment-success",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String paymentSuccessView() {
        return "pages/user/payment-success";
    }
}