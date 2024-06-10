package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsAdminResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourseBeforeLogin();

    List<CourseResponse> getAllCourseAfterLogin();

    List<CourseDetailsAdminResponse> getAllCourseForAdmin();

    List<CourseResponse> getMyCourse();

    CourseDetailsResponse getCourseDetails(Integer id);

    CourseDetailsAdminResponse addCourse(AddCourseRequest addCourseRequest);

    CourseDetailsAdminResponse updateCourse(Integer id, UpdateCourseRequest updateCourseRequest);

    CourseDetailsAdminResponse deleteCourse(Integer id);
}