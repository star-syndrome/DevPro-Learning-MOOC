package org.metrodataacademy.finalproject.serverapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(path = "/api")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(
            path = "/course/after-login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get all courses after login")
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<List<CourseResponse>> getAllCourseAfterLogin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseAfterLogin());
    }

    @GetMapping(
            path = "/admin/course",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to get all courses")
    @PreAuthorize(value = "hasAuthority('READ_ADMIN')")
    public ResponseEntity<List<CourseDetailsAdminResponse>> getAllCourseForAdmin() {
        return ResponseEntity.ok()
                .body(courseService.getAllCourseForAdmin());
    }

    @GetMapping(
            path = "/course/my-course",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get courses purchased by the user")
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<List<CourseResponse>> getCourseOrder() {
        return ResponseEntity.ok()
                .body(courseService.getMyCourse());
    }

    @GetMapping(
            path = "/course/details/{title}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get course by course title")
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<CourseDetailsResponse> courseDetails(@PathVariable String title) {
        return ResponseEntity.ok()
                .body(courseService.getCourseDetails(title));
    }

    @GetMapping(
            path = "/course/{title}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get card course information by course title")
    @PreAuthorize(value = "hasAuthority('READ_USER')")
    public ResponseEntity<CourseResponse> getCourseDetails(@PathVariable String title) {
        return ResponseEntity.ok()
                .body(courseService.getCourseByTitle(title));
    }

    @GetMapping(
            path = "/admin/course/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to get course details for update course")
    @PreAuthorize(value = "hasAuthority('READ_ADMIN')")
    public ResponseEntity<CourseDetailsAdminResponse> getCourseDetailsForAdmin(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(courseService.getCourseByIdForAdmin(id));
    }

    @PostMapping(
            path = "/admin/course",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to create course")
    @PreAuthorize(value = "hasAuthority('CREATE_ADMIN')")
    public ResponseEntity<CourseDetailsAdminResponse> addCourse(@Validated @RequestBody AddCourseRequest addCourseRequest){
        return ResponseEntity.ok()
                .body(courseService.addCourse(addCourseRequest));
    }

    @PutMapping(
            path = "/admin/course/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to update course")
    @PreAuthorize(value = "hasAuthority('UPDATE_ADMIN')")
    public ResponseEntity<CourseDetailsAdminResponse> updateCourse(
            @PathVariable Integer id, @Validated @RequestBody UpdateCourseRequest updateCourseRequest
    ){
        return ResponseEntity.ok()
                .body(courseService.updateCourse(id, updateCourseRequest));
    }

    @DeleteMapping(
            path = "/admin/course/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to delete course")
    @PreAuthorize(value = "hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<CourseDetailsAdminResponse> deleteCourse(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(courseService.deleteCourse(id));
    }
}