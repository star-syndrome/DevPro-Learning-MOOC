package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Module;
import org.metrodataacademy.finalproject.serverapp.repositories.ModuleRepository;
import org.metrodataacademy.finalproject.serverapp.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public ModuleResponse deleteModule(Integer id) {
        try {
            log.info("Try to delete module data with id {}", id);
            Module module = moduleRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found!"));

            moduleRepository.delete(module);
            log.info("Deleting the module with id {} was successful!", module.getId());

            return ModuleResponse.builder()
                    .name(module.getName())
                    .description(module.getDescription())
                    .content(module.getContent())
                    .duration(module.getDuration())
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }
}