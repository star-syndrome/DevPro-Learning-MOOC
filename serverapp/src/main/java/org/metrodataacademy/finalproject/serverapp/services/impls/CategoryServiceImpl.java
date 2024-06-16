package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CategoryResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Category;
import org.metrodataacademy.finalproject.serverapp.repositories.CategoryRepository;
import org.metrodataacademy.finalproject.serverapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategory() {
        log.info("Getting list of categories");
        return categoryRepository.findAll().stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getById(Integer id) {
        log.info("Try to get data category with id {}", id);
        return categoryRepository.findById(id)
                .map(this::mapToCategoryResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));
    }

    @Override
    public CategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        try {
            log.info("Process of adding new category {}", addCategoryRequest.getName());
            if (categoryRepository.existsByName(addCategoryRequest.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists!");
            }

            Category category = new Category();
            category.setName(addCategoryRequest.getName());

            categoryRepository.save(category);

            log.info("Adding new category was successful, new category: {}", category.getName());
            return mapToCategoryResponse(category);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        try {
            log.info("Try to update category data with name {}", updateCategoryRequest.getName());
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));

            if (categoryRepository.existsByName(updateCategoryRequest.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name already exists!");
            }

            category.setName(updateCategoryRequest.getName());
            categoryRepository.save(category);

            log.info("Updating category {} was successful!", updateCategoryRequest.getName());
            return mapToCategoryResponse(category);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public CategoryResponse deleteCategory(Integer id) {
        try {
            log.info("Try to delete category data with id {}", id);
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found!"));

            categoryRepository.delete(category);

            log.info("Deleting category with id: {} was successful!", id);
            return mapToCategoryResponse(category);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .linkPhoto(category.getLinkPhoto())
                .build();
    }
}