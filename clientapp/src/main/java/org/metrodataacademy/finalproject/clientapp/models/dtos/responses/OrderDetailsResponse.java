package org.metrodataacademy.finalproject.clientapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsResponse {

    private String title;
    private String category;
    private String mentor;
    private Integer price;
    private Double tax;
    private Double totalPrice;
}