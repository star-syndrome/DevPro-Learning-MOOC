package org.metrodataacademy.finalproject.clientapp.services.Impls;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.AddModuleRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateModuleRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.GetAllModulesResponse;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.ModuleResponse;
import org.metrodataacademy.finalproject.clientapp.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api/admin";

    @Override
    public List<GetAllModulesResponse> getAllModules() {
        return restTemplate
                .exchange(
                        url + "/module",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<GetAllModulesResponse>>() {}
                ).getBody();
    }

    @Override
    public GetAllModulesResponse getModuleById(Integer id) {
        return restTemplate
                .exchange(
                        url + "/module/" + id,
                        HttpMethod.GET,
                        null,
                        GetAllModulesResponse.class
                ).getBody();
    }

    @Override
    public GetAllModulesResponse createModule(AddModuleRequest addModuleRequest) {
        return restTemplate
                .exchange(
                        url + "/module",
                        HttpMethod.POST,
                        new HttpEntity<AddModuleRequest>(addModuleRequest),
                        GetAllModulesResponse.class
                ).getBody();
    }

    @Override
    public GetAllModulesResponse updateModule(Integer id, UpdateModuleRequest updateModuleRequest) {
        return restTemplate
                .exchange(
                        url + "/module/" + id,
                        HttpMethod.PUT,
                        new HttpEntity<UpdateModuleRequest>(updateModuleRequest),
                        GetAllModulesResponse.class
                ).getBody();
    }

    @Override
    public ModuleResponse deleteModule(Integer id) {
        return restTemplate
                .exchange(
                        url + "/module/" + id,
                        HttpMethod.DELETE,
                        null,
                        ModuleResponse.class
                ).getBody();
    }
}