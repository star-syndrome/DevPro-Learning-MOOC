package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourseBeforeLogin();

    List<CourseResponse> getAllCourseAfterLogin();

    List<CourseResponse> getMyCourse();

    CourseDetailsResponse getCourseDetails(Integer id);
}