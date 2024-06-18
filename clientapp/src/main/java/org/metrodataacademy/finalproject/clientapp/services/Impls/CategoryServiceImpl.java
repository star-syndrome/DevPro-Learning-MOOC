package org.metrodataacademy.finalproject.clientapp.services.Impls;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddCategoryRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateCategoryRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CategoryResponse;
import org.metrodataacademy.finalproject.clientapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api";

    @Override
    public List<CategoryResponse> getAllCategory() {
        return restTemplate
            .exchange(
                url + "/category",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryResponse>>() {}
            ).getBody();
    }

    @Override
    public CategoryResponse getById(Integer id) {
        return restTemplate
            .exchange(
                url + "/category/" + id,
                HttpMethod.GET,
                null,
                CategoryResponse.class
            ).getBody();
    }

    @Override
    public CategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        return restTemplate
            .exchange(
                url + "/admin/category",
                HttpMethod.POST,
                new HttpEntity<AddCategoryRequest>(addCategoryRequest),
                CategoryResponse.class
            ).getBody();
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        return restTemplate
            .exchange(
                url + "/admin/category/" + id,
                HttpMethod.PUT,
                new HttpEntity<UpdateCategoryRequest>(updateCategoryRequest),
                CategoryResponse.class
            ).getBody();
    }

    @Override
    public CategoryResponse deleteCategory(Integer id) {
        return restTemplate
            .exchange(
                url + "/admin/category/" + id,
                HttpMethod.DELETE,
                null,
                CategoryResponse.class
            ).getBody();
    }   
}