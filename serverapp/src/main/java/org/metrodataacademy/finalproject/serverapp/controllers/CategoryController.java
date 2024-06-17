package org.metrodataacademy.finalproject.serverapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateCategoryRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UploadImageRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.CategoryResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.MessageResponse;
import org.metrodataacademy.finalproject.serverapp.services.CategoryService;
import org.metrodataacademy.finalproject.serverapp.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/api")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping(
            path = "/category/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get category by id")
    @PreAuthorize(value = "hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PostMapping(path = "/admin/category/upload-image/{id}")
    @PreAuthorize(value = "hasAuthority('CREATE_ADMIN')")
    public ResponseEntity<MessageResponse> uploadImage(@PathVariable Integer id, @ModelAttribute UploadImageRequest uploadImageRequest) {
        return Optional.of(uploadImageRequest)
                .map(UploadImageRequest::getMultipartFile)
                .filter(file -> !file.isEmpty())
                .map(file -> new ResponseEntity<>(cloudinaryService.uploadImageForCategory(id, file), HttpStatus.OK))
                .orElse(new ResponseEntity<>(MessageResponse.builder()
                        .message("Upload image failed")
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping(
            path = "/admin/category",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to create category")
    @PreAuthorize(value = "hasAuthority('CREATE_ADMIN')")
    public ResponseEntity<CategoryResponse> addCategory(@Validated @RequestBody AddCategoryRequest addCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.addCategory(addCategoryRequest));
    }

    @PutMapping(
            path = "/admin/category/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to update category")
    @PreAuthorize(value = "hasAuthority('UPDATE_ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Integer id, @Validated @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.ok().body(categoryService.updateCategory(id, updateCategoryRequest));
    }

    @DeleteMapping(
            path = "/admin/category/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to delete category")
    @PreAuthorize(value = "hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }
}