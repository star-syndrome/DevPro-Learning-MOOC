package org.metrodataacademy.finalproject.serverapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRequest {

    private String recipient;
    private String subject;
    private String orderId;
    private String name;
    private String title;
    private Integer duration;
    private Double price;
    private String orderTime;
    private String paymentMethod;
    private Boolean paid;
}