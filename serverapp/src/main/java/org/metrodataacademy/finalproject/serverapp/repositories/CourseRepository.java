package org.metrodataacademy.finalproject.serverapp.repositories;

import org.metrodataacademy.finalproject.serverapp.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findById(Integer id);

    Boolean existsByTitle(String title);

    @Query("SELECT c FROM Course c LEFT JOIN Order o ON c.id = o.courses.id AND o.users.id = :id WHERE o.courses.id IS NULL")
    List<Course> getAllCourseAfterLogin(Integer id);

    @Query("SELECT c FROM Course c LEFT JOIN Order o ON o.courses.id = c.id WHERE o.users.id = :id")
    List<Course> getMyCourse(Integer id);

    @Query("SELECT COUNT(*) > 0 FROM Order o WHERE o.users.id = :user AND o.courses.id = :course")
    Boolean hasCourseOrder(Integer user, Integer course);
}