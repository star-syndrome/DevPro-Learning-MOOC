package org.metrodataacademy.finalproject.serverapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllCoursesAfterLogin {

    private String title;
    private Boolean isPremium;
    private Integer price;
    private String level;
    private String mentor;
    private String category;
}