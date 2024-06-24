package org.metrodataacademy.finalproject.clientapp.controllers.rest;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddCourseRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateCourseRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseDetailsAdminResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseResponse;
import org.metrodataacademy.finalproject.clientapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(
        path = "/admin/course",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseDetailsAdminResponse>> getAllCourseForAdmin() {
        return ResponseEntity.ok().body(courseService.getAllCourseForAdmin());
    }

    @GetMapping(
        path = "/course",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseResponse>> getAllCourse() {
        return ResponseEntity.ok().body(courseService.getAllCourse());
    }

    @GetMapping(
        path = "/course/{title}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseDetailsResponse> getCourseByTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(courseService.courseDetails(title));
    }

    @GetMapping(
        path = "/admin/course/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseDetailsAdminResponse> getCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(courseService.getCourseByIdForAdmin(id));
    }

    @GetMapping(
        path = "/admin/total-courses",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getTotalCourses() {
        return ResponseEntity.ok().body(courseService.countAllCourses());
    }

    @GetMapping(
        path = "/admin/total-premium-courses",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getPremiumCourses() {
        return ResponseEntity.ok().body(courseService.countPremiumCourses());
    }

    @PostMapping(
        path = "/admin/course",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseDetailsAdminResponse> addCourse(@RequestBody AddCourseRequest addCourseRequest) {
        return ResponseEntity.ok().body(courseService.addCourse(addCourseRequest));
    }

    @PutMapping(
        path = "/admin/course/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseDetailsAdminResponse> updateCourse(@PathVariable Integer id, @RequestBody UpdateCourseRequest updateCourseRequest) {
        return ResponseEntity.ok().body(courseService.updateCourse(id, updateCourseRequest));
    }

    @DeleteMapping(
        path = "/admin/course/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseDetailsAdminResponse> deleteCourse(@PathVariable Integer id) {
        return ResponseEntity.ok().body(courseService.deleteCourse(id));
    }
}