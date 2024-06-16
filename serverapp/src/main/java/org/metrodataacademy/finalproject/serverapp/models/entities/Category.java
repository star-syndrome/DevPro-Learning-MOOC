package org.metrodataacademy.finalproject.serverapp.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private String linkPhoto;

    @OneToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;
}