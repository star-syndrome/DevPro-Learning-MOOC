package org.metrodataacademy.finalproject.clientapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String course;
    private String name;
    private String time;
    private String payment;
    private Boolean isPaid;
}