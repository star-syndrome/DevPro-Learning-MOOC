package org.metrodataacademy.finalproject.clientapp.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String country;
    private String username;
    private String password;
    private List<Role> roles;
}