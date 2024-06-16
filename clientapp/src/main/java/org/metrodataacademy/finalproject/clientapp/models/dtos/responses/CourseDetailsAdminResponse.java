package org.metrodataacademy.finalproject.clientapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailsAdminResponse {

    private Integer id;
    private String title;
    private Boolean isPremium;
    private Integer price;
    private String level;
    private String mentor;
    private String about;
    private Integer totalDuration;
    private List<ModuleAdminResponse> moduleAdminResponses;
}