package org.metrodataacademy.finalproject.serverapp.controllers;

import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;
import org.metrodataacademy.finalproject.serverapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(
            path = "/getAllCourseBeforeLogin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseResponse>> getAllCourseBeforeLogin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseBeforeLogin());
    }

    @GetMapping(
            path = "/getAllCourseAfterLogin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseResponse>> getAllCourseAfterLogin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseAfterLogin());
    }

    @GetMapping(
            path = "/getMyCourse",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CourseResponse>> getCourseOrder() {
        return ResponseEntity.ok()
                .body(courseService.getMyCourse());
    }

    @GetMapping(
            path = "/details/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CourseDetailsResponse> courseDetails(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(courseService.getCourseDetails(id));
    }
}