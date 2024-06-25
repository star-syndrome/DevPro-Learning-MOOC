package org.metrodataacademy.finalproject.serverapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllModulesResponse {

    private Integer id;
    private String name;
    private String description;
    private String content;
    private Integer duration;
    private String course;
}