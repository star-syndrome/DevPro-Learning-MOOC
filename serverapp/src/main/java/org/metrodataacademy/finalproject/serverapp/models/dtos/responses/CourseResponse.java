package org.metrodataacademy.finalproject.serverapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private String title;
    private String isPremium;
    private Integer price;
    private String level;
    private String mentor;
    private String category;
    private String linkPhoto;
    private Integer totalDuration;
}