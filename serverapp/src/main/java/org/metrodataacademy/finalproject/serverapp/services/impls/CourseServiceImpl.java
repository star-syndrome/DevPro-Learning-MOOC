package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCourseRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CourseResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;
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

    @Override
    public CourseResponse addCourse(AddCourseRequest addCourseRequest) {
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
                    .totalDuration(addCourseRequest.getTotalDuration())
                    .modules(modules)
                    .categories(category)
                    .users(user)
                    .build();
            courseRepository.save(course);

            log.info("Process of adding a new course is completed, new course: {}", course.getTitle());
            return mapToCourseResponse(course);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public CourseResponse updateCourse(Integer id, UpdateCourseRequest updateCourseRequest) {
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
            course.setTotalDuration(updateCourseRequest.getTotalDuration());
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
            courseRepository.save(course);

            log.info("Updating the course with id {} was successful!", course.getId());
            return mapToCourseResponse(course);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public CourseResponse deleteCourse(Integer id) {
        try {
            log.info("Try to delete course data with id {}", id);
            Course course = courseRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            courseRepository.delete(course);
            log.info("Deleting the course with id {} was successful!", course.getId());

            return mapToCourseResponse(course);
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