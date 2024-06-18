package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.*;
import org.metrodataacademy.finalproject.serverapp.models.entities.Category;
import org.metrodataacademy.finalproject.serverapp.models.entities.Course;
import org.metrodataacademy.finalproject.serverapp.models.entities.Module;
import org.metrodataacademy.finalproject.serverapp.models.entities.User;
import org.metrodataacademy.finalproject.serverapp.repositories.CategoryRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.CourseRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.ModuleRepository;
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
    private ModuleRepository moduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
    public List<CourseDetailsAdminResponse> getAllCourseForAdmin() {
        log.info("Getting list of courses for admin!");
        return courseRepository.findAll().stream()
                .map(this::mapToCourseDetailsAdminResponse)
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
    public CourseResponse getCourseByTitle(String title) {
        try {
            log.info("Getting course information from course title {} ", title);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            Course course = courseRepository.findCourseByTitle(title)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            return courseRepository.findCourseByTitle(course.getTitle())
                    .map(this::mapToCourseResponse)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDetailsResponse getCourseDetails(String title) {
        try {
            log.info("Getting course details information from course title {} ", title);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User users = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            Course course = courseRepository.findCourseByTitle(title)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            Boolean hasCourseOrder = courseRepository.hasCourseOrder(users.getId(), course.getId());

            return courseRepository.findCourseByTitle(course.getTitle())
                    .map(courses -> CourseDetailsResponse.builder()
                            .title(courses.getTitle())
                            .about(courses.getAbout())
                            .price(courses.getPrice())
                            .isPremium(courses.getIsPremium())
                            .level(courses.getLevel())
                            .mentor(courses.getMentor())
                            .totalDuration(courses.getModules().stream().mapToInt(Module::getDuration).sum())
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

    @Override
    public CourseDetailsAdminResponse addCourse(AddCourseRequest addCourseRequest) {
        try {
            log.info("Process of adding new course {}", addCourseRequest.getTitle());
            if (courseRepository.existsByTitle(addCourseRequest.getTitle())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course title already exists!");
            }

            Category category = categoryRepository.findById(addCourseRequest.getCategoryId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));

            User user = userRepository.findById(1) // <- Admin ID
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            List<Module> modules = addCourseRequest.getAddModuleRequests().stream()
                    .map(addModuleRequest -> Module.builder()
                            .name(addModuleRequest.getName())
                            .description(addModuleRequest.getDescription())
                            .content(addModuleRequest.getContent())
                            .duration(addModuleRequest.getDuration())
                            .build())
                    .collect(Collectors.toList());

            Course course = Course.builder()
                    .title(addCourseRequest.getTitle())
                    .isPremium(addCourseRequest.getIsPremium())
                    .price(addCourseRequest.getPrice())
                    .level(addCourseRequest.getLevel())
                    .mentor(addCourseRequest.getMentor())
                    .about(addCourseRequest.getAbout())
                    .totalDuration(modules.stream().mapToInt(Module::getDuration).sum())
                    .modules(modules)
                    .categories(category)
                    .users(user)
                    .build();
            courseRepository.save(course);

            log.info("Process of adding a new course is completed, new course: {}", course.getTitle());
            return mapToCourseDetailsAdminResponse(course);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDetailsAdminResponse getCourseByIdForAdmin(Integer id) {
        log.info("Getting course details information from course id {} ", id);
        return courseRepository.findById(id)
                .map(this::mapToCourseDetailsAdminResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));
    }

    @Override
    public CourseDetailsAdminResponse updateCourse(Integer id, UpdateCourseRequest updateCourseRequest) {
        try {
            log.info("Try to update course data with id {}", id);
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            if (courseRepository.existsByTitleAndNotId(updateCourseRequest.getTitle(), id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course title already exists!");
            }

            List<Module> modules = course.getModules();
            for (UpdateModuleRequest moduleRequest : updateCourseRequest.getUpdateModuleRequests()) {
                Module module = modules.stream()
                        .filter(mdl -> mdl.getName().equals(moduleRequest.getName()))
                        .findFirst()
                        .orElse(null);
                if (module != null) {
                    continue;
                }
                if (moduleRepository.existsByNameAndNotId(moduleRequest.getName(), -1)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Module name already exists!");
                }
            }

            Category category = categoryRepository.findById(updateCourseRequest.getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));

            User user = userRepository.findById(1) // <- Admin ID
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            course.setTitle(updateCourseRequest.getTitle());
            course.setIsPremium(updateCourseRequest.getIsPremium());
            course.setPrice(updateCourseRequest.getPrice());
            course.setLevel(updateCourseRequest.getLevel());
            course.setMentor(updateCourseRequest.getMentor());
            course.setAbout(updateCourseRequest.getAbout());
            course.setCategories(category);
            course.setUsers(user);

            List<Module> moduleList = updateCourseRequest.getUpdateModuleRequests().stream()
                    .map(updateModuleRequest -> {
                        Module module = modules.stream()
                                .filter(mdl -> mdl.getName().equals(updateModuleRequest.getName()))
                                .findFirst()
                                .orElse(new Module());
                        module.setName(updateModuleRequest.getName());
                        module.setDescription(updateModuleRequest.getDescription());
                        module.setContent(updateModuleRequest.getContent());
                        module.setDuration(updateModuleRequest.getDuration());
                        return module;
                    }).collect(Collectors.toList());

            course.setModules(moduleList);
            course.setTotalDuration(moduleList.stream().mapToInt(Module::getDuration).sum());
            courseRepository.save(course);

            log.info("Updating the course with id {} was successful!", course.getId());
            return mapToCourseDetailsAdminResponse(course);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public CourseDetailsAdminResponse deleteCourse(Integer id) {
        try {
            log.info("Try to delete course data with id {}", id);
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            courseRepository.delete(course);
            log.info("Deleting the course with id {} was successful!", course.getId());

            return mapToCourseDetailsAdminResponse(course);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAllCourses() {
        log.info("Get total of all courses!");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return courseRepository.countTotalCourses(user.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Long countPremiumCourses() {
        log.info("Get total of all premium courses!");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return courseRepository.countByIsPremium(user.getId());
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

    private CourseDetailsAdminResponse mapToCourseDetailsAdminResponse(Course course) {
        return CourseDetailsAdminResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .about(course.getAbout())
                .price(course.getPrice())
                .isPremium(course.getIsPremium())
                .level(course.getLevel())
                .mentor(course.getMentor())
                .totalDuration(course.getTotalDuration())
                .moduleAdminResponses(course.getModules().stream()
                        .map(module -> ModuleAdminResponse.builder()
                                .id(module.getId())
                                .name(module.getName())
                                .description(module.getDescription())
                                .content(module.getContent())
                                .duration(module.getDuration())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}