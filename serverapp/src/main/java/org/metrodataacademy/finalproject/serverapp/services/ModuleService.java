package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.GetAllModulesResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;

import java.util.List;

public interface ModuleService {

    List<GetAllModulesResponse> getAllModules();

    GetAllModulesResponse getModuleById(Integer id);

    GetAllModulesResponse createModule(AddModuleRequest addModuleRequest);

    GetAllModulesResponse updateModule(Integer id, UpdateModuleRequest updateModuleRequest);

    ModuleResponse deleteModule(Integer id);
}