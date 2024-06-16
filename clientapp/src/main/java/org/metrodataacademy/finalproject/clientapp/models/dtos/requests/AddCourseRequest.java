package org.metrodataacademy.finalproject.clientapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCourseRequest {

    private String title;
    private Boolean isPremium;
    private Integer price;
    private String level;
    private String mentor;
    private String about;
    private Integer categoryId;
    private List<AddModuleRequest> addModuleRequests;
}