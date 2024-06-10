package org.metrodataacademy.finalproject.serverapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull
    private Integer paymentId;

    @NotBlank
    private String title;
}