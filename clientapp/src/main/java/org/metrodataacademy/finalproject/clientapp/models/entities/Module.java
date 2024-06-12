package org.metrodataacademy.finalproject.clientapp.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module {

    private Integer id;
    private String name;
    private String description;
    private String content;
    private Integer duration;
    private Course courses;
}