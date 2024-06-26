package org.metrodataacademy.finalproject.clientapp.controllers;

import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.OrderDetailsResponse;
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
        OrderDetailsResponse orderDetailsResponse = orderService.getOrderDetailsCourse(title);
        model.addAttribute("order", orderDetailsResponse);
        return "pages/user/payment-page";
    }  
}