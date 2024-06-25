package org.metrodataacademy.finalproject.clientapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleRequest {

    private String name;
    private String description;
    private String content;
    private Integer duration;
}