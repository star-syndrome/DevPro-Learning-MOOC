package org.metrodataacademy.finalproject.serverapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModuleRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String content;

    @NotNull
    private Integer duration;

    @NotNull
    private Integer courseId;
}