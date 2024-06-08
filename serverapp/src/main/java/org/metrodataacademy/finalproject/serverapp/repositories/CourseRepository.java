package org.metrodataacademy.finalproject.serverapp.repositories;

import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.GetAllCoursesAfterLogin;
import org.metrodataacademy.finalproject.serverapp.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findById(Integer id);

    @Query("SELECT c FROM Course c LEFT JOIN Order o ON c.id = o.courses.id WHERE o.courses.id IS NULL")
    List<GetAllCoursesAfterLogin> getAllCourseAfterLogin();
}