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
@Table(name = "tb_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Boolean isPremium;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 25)
    private String level;

    @Column(nullable = false, length = 50)
    private String mentor;

    @Column(nullable = false)
    private String about;

    @Column(nullable = false)
    private Integer totalDuration;

    @OneToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Module> modules;

    @OneToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_course1"))
    private User users;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_course2"))
    private Category categories;
}