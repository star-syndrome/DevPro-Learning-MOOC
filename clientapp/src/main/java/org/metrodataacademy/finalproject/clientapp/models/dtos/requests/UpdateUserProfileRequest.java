package org.metrodataacademy.finalproject.clientapp.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {

    private String name;
    private String email;
    private String phone;
    private String city;
    private String country;
}