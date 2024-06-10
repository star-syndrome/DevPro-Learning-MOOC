package org.metrodataacademy.finalproject.serverapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String course;
    private String username;
    private String time;
    private String payment;
    private Boolean isPaid;
}