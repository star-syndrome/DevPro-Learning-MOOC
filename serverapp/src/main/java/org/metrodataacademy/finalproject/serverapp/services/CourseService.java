package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.GetAllCoursesBeforeLogin;

import java.util.List;

public interface CourseService {

    List<GetAllCoursesBeforeLogin> getAllCourseBeforeLogin();

}