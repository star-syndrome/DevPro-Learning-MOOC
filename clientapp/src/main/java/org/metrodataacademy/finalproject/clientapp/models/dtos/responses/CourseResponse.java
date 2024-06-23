package org.metrodataacademy.finalproject.clientapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}