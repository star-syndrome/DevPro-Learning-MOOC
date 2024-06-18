package org.metrodataacademy.finalproject.clientapp.services;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddCategoryRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateCategoryRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CategoryResponse;

public interface CategoryService {

    List<CategoryResponse> getAllCategory();

    CategoryResponse getById(Integer id);

    CategoryResponse addCategory(AddCategoryRequest addCategoryRequest);

    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryRequest);

    CategoryResponse deleteCategory(Integer id);
}