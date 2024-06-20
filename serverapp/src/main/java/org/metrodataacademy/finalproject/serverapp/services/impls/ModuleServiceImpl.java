package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.AddModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.GetAllModulesResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.ModuleResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Course;
import org.metrodataacademy.finalproject.serverapp.models.entities.Module;
import org.metrodataacademy.finalproject.serverapp.repositories.CourseRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.ModuleRepository;
import org.metrodataacademy.finalproject.serverapp.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GetAllModulesResponse> getAllModules() {
        log.info("Get All Modules");
        return moduleRepository.findAll().stream()
                .map(this::mapToModuleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GetAllModulesResponse getModuleById(Integer id) {
        log.info("Get module by id {}", id);
        return moduleRepository.findById(id)
                .map(this::mapToModuleResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found!"));
    }

    @Override
    public GetAllModulesResponse createModule(AddModuleRequest addModuleRequest) {
        try {
            log.info("Process of adding new module {}", addModuleRequest.getName());
            if (moduleRepository.existsByName(addModuleRequest.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Module name already exists!");
            }

            Course course = courseRepository.findById(addModuleRequest.getCourseId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            Module module = Module.builder()
                    .name(addModuleRequest.getName())
                    .description(addModuleRequest.getDescription())
                    .content(addModuleRequest.getContent())
                    .duration(addModuleRequest.getDuration())
                    .courses(course)
                    .build();
            moduleRepository.save(module);

            course.setTotalDuration(course.getModules().stream().mapToInt(Module::getDuration).sum());
            courseRepository.save(course);

            log.info("Process of adding a new module is completed, new module: {}", addModuleRequest.getName());
            return mapToModuleResponse(module);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public GetAllModulesResponse updateModule(Integer id, UpdateModuleRequest updateModuleRequest) {
        try {
            log.info("Try to update module data with id {}", id);
            Module module = moduleRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found!"));

            if (moduleRepository.existsByNameAndNotId(updateModuleRequest.getName(), module.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Module name already exists!");
            }

            Course course = courseRepository.findById(updateModuleRequest.getCourseId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found!"));

            module.setName(updateModuleRequest.getName());
            module.setDescription(updateModuleRequest.getDescription());
            module.setContent(updateModuleRequest.getContent());
            module.setDuration(updateModuleRequest.getDuration());
            module.setCourses(course);
            moduleRepository.save(module);

            course.setTotalDuration(course.getModules().stream().mapToInt(Module::getDuration).sum());
            courseRepository.save(course);

            log.info("Updating the module with id {} was successful!", updateModuleRequest.getName());
            return mapToModuleResponse(module);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

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

    private GetAllModulesResponse mapToModuleResponse(Module module) {
        return GetAllModulesResponse.builder()
                .name(module.getName())
                .description(module.getDescription())
                .content(module.getContent())
                .duration(module.getDuration())
                .courseId(module.getCourses().getId())
                .build();
    }
}