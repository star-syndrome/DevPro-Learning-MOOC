package org.metrodataacademy.finalproject.clientapp.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleAdminResponse {

    private Integer id;
    private String name;
    private String description;
    private String content;
    private Integer duration;
}