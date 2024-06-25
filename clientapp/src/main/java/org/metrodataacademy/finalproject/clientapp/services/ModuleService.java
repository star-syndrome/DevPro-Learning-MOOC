package org.metrodataacademy.finalproject.clientapp.services;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddModuleRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.GetAllModulesResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.ModuleResponse;

import java.util.List;

public interface ModuleService {

    List<GetAllModulesResponse> getAllModules();

    GetAllModulesResponse getModuleById(Integer id);

    GetAllModulesResponse createModule(AddModuleRequest addModuleRequest);

    GetAllModulesResponse updateModule(Integer id, UpdateModuleRequest updateModuleRequest);

    ModuleResponse deleteModule(Integer id);
}