package org.metrodataacademy.finalproject.clientapp.services.Impls;

import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.ChangePasswordRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.UpdateUserProfileRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.UserResponse;
import org.metrodataacademy.finalproject.clientapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api";

    @Override
    public UserResponse getUserProfile() {
        return restTemplate
                .exchange(
                        url + "/profile/get",
                        HttpMethod.GET,
                        null,
                        UserResponse.class
                ).getBody();
    }

    @Override
    public UserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) {
        return restTemplate
                .exchange(
                        url + "/profile/update",
                        HttpMethod.PUT,
                        new HttpEntity<UpdateUserProfileRequest>(updateUserProfileRequest),
                        UserResponse.class
                ).getBody();
    }

    @Override
    public UserResponse changePasswordUser(ChangePasswordRequest changePasswordRequest) {
        return restTemplate
                .exchange(
                        url + "/profile/changePassword",
                        HttpMethod.PUT,
                        new HttpEntity<ChangePasswordRequest>(changePasswordRequest),
                        UserResponse.class
                ).getBody();
    }

    @Override
    public Long countAllUsers() {
        return restTemplate
                .exchange(
                        url + "/admin/total-users",
                        HttpMethod.GET,
                        null,
                        Long.class
                ).getBody();
    }
}