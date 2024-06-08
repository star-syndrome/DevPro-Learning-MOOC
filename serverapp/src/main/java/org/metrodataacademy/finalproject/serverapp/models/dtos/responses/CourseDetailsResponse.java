package org.metrodataacademy.finalproject.serverapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailsResponse {

    private String title;
    private Boolean isPremium;
    private Integer price;
    private String level;
    private String mentor;
    private String about;
    private Integer totalDuration;
    private List<ModuleResponse> moduleResponses;
}