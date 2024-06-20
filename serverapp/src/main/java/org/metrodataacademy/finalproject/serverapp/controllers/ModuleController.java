package org.metrodataacademy.finalproject.serverapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.GetAllModulesResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;
import org.metrodataacademy.finalproject.serverapp.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
@PreAuthorize(value = "hasRole('ADMIN')")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping(
            path = "/module",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get all module")
    @PreAuthorize(value = "hasAuthority('READ_ADMIN')")
    public ResponseEntity<List<GetAllModulesResponse>> getAllPayment() {
        return ResponseEntity.ok().body(moduleService.getAllModules());
    }

    @GetMapping(
            path = "/module/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API to get module by id")
    @PreAuthorize(value = "hasAuthority('READ_ADMIN')")
    public ResponseEntity<GetAllModulesResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(moduleService.getModuleById(id));
    }

    @PostMapping(
            path = "/module",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to create module")
    @PreAuthorize(value = "hasAuthority('CREATE_ADMIN')")
    public ResponseEntity<GetAllModulesResponse> addModule(@Validated @RequestBody AddModuleRequest addModuleRequest) {
        return ResponseEntity.ok().body(moduleService.createModule(addModuleRequest));
    }

    @PutMapping(
            path = "/module/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to update module")
    @PreAuthorize(value = "hasAuthority('UPDATE_ADMIN')")
    public ResponseEntity<GetAllModulesResponse> updateModule(
            @PathVariable Integer id, @Validated @RequestBody UpdateModuleRequest updateModuleRequest) {
        return ResponseEntity.ok().body(moduleService.updateModule(id, updateModuleRequest));
    }

    @DeleteMapping(
            path = "/module/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "API for admin to delete module")
    @PreAuthorize(value = "hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<ModuleResponse> deleteModule(@PathVariable Integer id) {;
        return ResponseEntity.ok().body(moduleService.deleteModule(id));
    }
}