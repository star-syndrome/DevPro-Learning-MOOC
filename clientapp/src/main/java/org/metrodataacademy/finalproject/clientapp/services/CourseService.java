package org.metrodataacademy.finalproject.clientapp.services;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddCourseRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateCourseRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseDetailsAdminResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseResponse;

public interface CourseService {

    List<CourseDetailsAdminResponse> getAllCourseForAdmin();
    
    CourseDetailsAdminResponse getCourseByIdForAdmin(Integer id);
    
    CourseDetailsAdminResponse addCourse(AddCourseRequest addCourseRequest);

    CourseDetailsAdminResponse updateCourse(Integer id, UpdateCourseRequest updateCourseRequest);

    CourseDetailsAdminResponse deleteCourse(Integer id);

    List<CourseResponse> getAllCourse();

    CourseDetailsResponse courseDetails(String title);

    Long countAllCourses();

    Long countPremiumCourses();
}