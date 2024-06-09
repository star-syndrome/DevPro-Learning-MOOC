package org.metrodataacademy.finalproject.serverapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddModuleRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String content;

    @NotBlank
    private Integer duration;
}