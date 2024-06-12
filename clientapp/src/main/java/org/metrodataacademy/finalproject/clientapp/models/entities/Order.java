package org.metrodataacademy.finalproject.clientapp.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;
    private Date time;
    private Boolean isPaid;
    private Payment payments;
    private User users;
    private Course courses;
}