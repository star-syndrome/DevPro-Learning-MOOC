package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.GetAllCoursesBeforeLogin;
import org.metrodataacademy.finalproject.serverapp.repositories.CourseRepository;
import org.metrodataacademy.finalproject.serverapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GetAllCoursesBeforeLogin> getAllCourseBeforeLogin() {
        log.info("Getting list of course before login!");
        return courseRepository.findAll().stream()
                .map(course -> GetAllCoursesBeforeLogin.builder()
                        .title(course.getTitle())
                        .isPremium(course.getIsPremium())
                        .price(course.getPrice())
                        .level(course.getLevel())
                        .mentor(course.getMentor())
                        .category(course.getCategories().getName())
                        .build())
                .collect(Collectors.toList());
    }
}