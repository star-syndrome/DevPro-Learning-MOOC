package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Course;
import org.metrodataacademy.finalproject.serverapp.models.entities.User;
import org.metrodataacademy.finalproject.serverapp.repositories.CourseRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.UserRepository;
import org.metrodataacademy.finalproject.serverapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourseBeforeLogin() {
        log.info("Getting list of courses before login!");
        return courseRepository.findAll().stream()
                .map(this::mapToCourseResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourseAfterLogin() {
        log.info("Getting list of courses after login!");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User users = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return courseRepository.getAllCourseAfterLogin(users.getId()).stream()
                .map(this::mapToCourseResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getMyCourse() {
        log.info("Getting my courses!");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User users = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return courseRepository.getMyCourse(users.getId()).stream()
                .map(this::mapToCourseResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDetailsResponse getCourseDetails(Integer id) {
        try {
            log.info("Getting course details information from course id {} ", id);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User users = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
            Boolean hasCourseOrder = courseRepository.hasCourseOrder(users.getId(), course.getId());

            return courseRepository.findById(course.getId())
                    .map(courses -> CourseDetailsResponse.builder()
                            .title(courses.getTitle())
                            .about(courses.getAbout())
                            .price(courses.getPrice())
                            .isPremium(courses.getIsPremium())
                            .level(courses.getLevel())
                            .mentor(courses.getMentor())
                            .totalDuration(courses.getTotalDuration())
                            .moduleResponses(courses.getModules().stream()
                                    .map(module -> ModuleResponse.builder()
                                            .name(module.getName())
                                            .description(module.getDescription())
                                            .content(!courses.getIsPremium() ? module.getContent() :
                                                    hasCourseOrder ? module.getContent() : "Content not available!")
                                            .duration(module.getDuration())
                                            .build())
                                    .collect(Collectors.toList()))
                            .build())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    private CourseResponse mapToCourseResponse(Course course) {
        return CourseResponse.builder()
                .title(course.getTitle())
                .isPremium(course.getIsPremium())
                .price(course.getPrice())
                .level(course.getLevel())
                .mentor(course.getMentor())
                .category(course.getCategories().getName())
                .build();
    }
}