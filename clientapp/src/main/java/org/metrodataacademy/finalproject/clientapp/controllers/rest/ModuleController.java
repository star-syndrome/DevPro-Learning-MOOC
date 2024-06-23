package org.metrodataacademy.finalproject.clientapp.controllers.rest;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddModuleRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.GetAllModulesResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.ModuleResponse;
import org.metrodataacademy.finalproject.clientapp.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping(
            path = "/module",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GetAllModulesResponse>> getAllModules() {
        return ResponseEntity.ok().body(moduleService.getAllModules());
    }

    @GetMapping(
            path = "/module/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllModulesResponse> getModuleById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(moduleService.getModuleById(id));
    }

    @PostMapping(
            path = "/module",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllModulesResponse> createModule(@RequestBody AddModuleRequest addModuleRequest) {
        return ResponseEntity.ok().body(moduleService.createModule(addModuleRequest));
    }

    @PutMapping(
            path = "/module/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllModulesResponse> updateModule(@PathVariable Integer id, @RequestBody UpdateModuleRequest updateModuleRequest) {
        return ResponseEntity.ok().body(moduleService.updateModule(id, updateModuleRequest));
    }

    @DeleteMapping(
            path = "/module/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ModuleResponse> deleteModule(@PathVariable Integer id) {
        return ResponseEntity.ok().body(moduleService.deleteModule(id));
    }
}