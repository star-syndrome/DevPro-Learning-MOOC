package org.metrodataacademy.finalproject.clientapp.services.Impls;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddCourseRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateCourseRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseDetailsAdminResponse;
import org.metrodataacademy.finalproject.clientapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api/admin";

    @Override
    public List<CourseDetailsAdminResponse> getAllCourseForAdmin() {
        return restTemplate
            .exchange(
                url + "/course",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CourseDetailsAdminResponse>>() {})
                .getBody();
    }
    
    @Override
    public CourseDetailsAdminResponse getCourseByIdForAdmin(Integer id) {
        return restTemplate
            .exchange(
                url + "/course/" + id,
                HttpMethod.GET,
                null,
                CourseDetailsAdminResponse.class
            ).getBody();
    }

    @Override
    public CourseDetailsAdminResponse addCourse(AddCourseRequest addCourseRequest) {
        return restTemplate
            .exchange(
                url + "/course",
                HttpMethod.POST,
                new HttpEntity<AddCourseRequest>(addCourseRequest),
                CourseDetailsAdminResponse.class
            ).getBody();
    }

    @Override
    public CourseDetailsAdminResponse updateCourse(Integer id, UpdateCourseRequest updateCourseRequest) {
        return restTemplate
            .exchange(
                url + "/course/" + id,
                HttpMethod.PUT,
                new HttpEntity<UpdateCourseRequest>(updateCourseRequest),
                CourseDetailsAdminResponse.class
            ).getBody();
    }

    @Override
    public CourseDetailsAdminResponse deleteCourse(Integer id) {
        return restTemplate
            .exchange(
                url + "/course/" + id,
                HttpMethod.DELETE,
                null,
                CourseDetailsAdminResponse.class
            ).getBody();
    }

    @Override
    public Long countAllCourses() {
        return restTemplate
            .exchange(
                url + "/total-courses",
                HttpMethod.GET,
                null,
                Long.class
            ).getBody();
    }

    @Override
    public Long countPremiumCourses() {
        return restTemplate
            .exchange(
                url + "/total-premium-courses",
                HttpMethod.GET,
                null,
                Long.class
            ).getBody();
    }
}