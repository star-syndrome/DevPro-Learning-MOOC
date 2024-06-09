package org.metrodataacademy.finalproject.serverapp.services;

import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;

public interface ModuleService {

    ModuleResponse deleteModule(Integer id);
}