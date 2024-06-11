package org.metrodataacademy.finalproject.serverapp.controllers;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CategoryResponse;
import org.metrodataacademy.finalproject.serverapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(
            path = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    public ResponseEntity<List<CategoryResponse>> getAllPayment() {
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('CREATE_ADMIN')")
    public ResponseEntity<CategoryResponse> addPayment(@Validated @RequestBody AddCategoryRequest addCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.addCategory(addCategoryRequest));
    }

    @PutMapping(
            path = "/update/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('UPDATE_ADMIN')")
    public ResponseEntity<CategoryResponse> updatePayment(
            @PathVariable Integer id, @Validated @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.updateCategory(id, updateCategoryRequest));
    }

    @DeleteMapping(
            path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<CategoryResponse> deletePayment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }
}