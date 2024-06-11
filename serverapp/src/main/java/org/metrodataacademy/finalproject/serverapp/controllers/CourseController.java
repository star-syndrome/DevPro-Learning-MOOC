package org.metrodataacademy.finalproject.serverapp.controllers;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsAdminResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;
import org.metrodataacademy.finalproject.serverapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(
            path = "/getAllCourseAfterLogin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<List<CourseResponse>> getAllCourseAfterLogin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseAfterLogin());
    }

    @GetMapping(
            path = "/getAllCourseForAdmin",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('READ_ADMIN')")
    public ResponseEntity<List<CourseDetailsAdminResponse>> getAllCourseForAdmin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseForAdmin());
    }

    @GetMapping(
            path = "/getMyCourse",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<List<CourseResponse>> getCourseOrder() {
        return ResponseEntity.ok()
                .body(courseService.getMyCourse());
    }

    @GetMapping(
            path = "/details/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<CourseDetailsResponse> courseDetails(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(courseService.getCourseDetails(id));
    }

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('CREATE_ADMIN')")
    public ResponseEntity<CourseDetailsAdminResponse> addCourse(@Validated @RequestBody AddCourseRequest addCourseRequest){
        return ResponseEntity.ok()
                .body(courseService.addCourse(addCourseRequest));
    }

    @PutMapping(
            path = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('UPDATE_ADMIN')")
    public ResponseEntity<CourseDetailsAdminResponse> updateCourse(
            @PathVariable Integer id, @Validated @RequestBody UpdateCourseRequest updateCourseRequest
    ){
        return ResponseEntity.ok()
                .body(courseService.updateCourse(id, updateCourseRequest));
    }

    @DeleteMapping(
            path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<CourseDetailsAdminResponse> deleteCourse(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(courseService.deleteCourse(id));
    }
}