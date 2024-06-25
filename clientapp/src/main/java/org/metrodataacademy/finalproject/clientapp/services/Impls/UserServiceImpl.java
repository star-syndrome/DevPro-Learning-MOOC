package org.metrodataacademy.finalproject.clientapp.services.Impls;

import org.metrodataacademy.finalproject.clientapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Long countAllUsers() {
        String url = "http://localhost:8080/api/admin";
        return restTemplate
                .exchange(
                        url + "/total-users",
                        HttpMethod.GET,
                        null,
                        Long.class
                ).getBody();
    }
}
