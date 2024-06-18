package org.metrodataacademy.finalproject.clientapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String category;
    private String course;
    private String username;
    private String time;
    private String payment;
    private String isPaid;
}