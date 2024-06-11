package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategory();

    CategoryResponse getById(Integer id);

    CategoryResponse addCategory(AddCategoryRequest addCategoryRequest);

    CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryRequest);

    CategoryResponse deleteCategory(Integer id);
}