package org.metrodataacademy.finalproject.serverapp.controllers;

import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;
import org.metrodataacademy.finalproject.serverapp.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/module")
@PreAuthorize(value = "hasRole('ADMIN')")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @DeleteMapping(
            path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize(value = "hasAuthority('DELETE_ADMIN')")
    public ResponseEntity<ModuleResponse> deleteModule(@PathVariable Integer id) {;
        return ResponseEntity.ok()
                .body(moduleService.deleteModule(id));
    }
}