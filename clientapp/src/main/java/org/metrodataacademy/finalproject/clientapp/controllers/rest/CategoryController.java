package org.metrodataacademy.finalproject.clientapp.controllers.rest;

import java.util.List;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddCategoryRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateCategoryRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CategoryResponse;
import org.metrodataacademy.finalproject.clientapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(
        path = "/category",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @GetMapping(
        path = "/category/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PostMapping(
        path = "/admin/category",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.addCategory(addCategoryRequest));
    }

    @PutMapping(
        path = "/admin/category/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Integer id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.updateCategory(id, updateCategoryRequest));
    }

    @DeleteMapping(
        path = "/admin/category/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }   
}