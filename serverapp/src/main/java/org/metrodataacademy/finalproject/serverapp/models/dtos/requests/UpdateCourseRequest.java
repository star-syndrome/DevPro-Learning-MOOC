package org.metrodataacademy.finalproject.serverapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotNull
    private Boolean isPremium;

    @NotNull
    private Integer price;

    @NotBlank
    @Size(max = 25)
    private String level;

    @NotBlank
    @Size(max = 50)
    private String mentor;

    @NotBlank
    private String about;

    @NotNull
    private Integer categoryId;

    @NotNull
    private List<ModuleRequest> moduleRequests;
}