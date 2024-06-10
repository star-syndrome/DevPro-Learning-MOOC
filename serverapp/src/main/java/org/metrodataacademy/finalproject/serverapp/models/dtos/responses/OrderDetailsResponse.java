package org.metrodataacademy.finalproject.serverapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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