package org.metrodataacademy.finalproject.clientapp.services.Impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.clientapp.models.dtos.requests.LoginRequest;
import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.LoginResponse;
import org.metrodataacademy.finalproject.clientapp.services.AuthService;
import org.metrodataacademy.finalproject.clientapp.utils.AuthSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api/auth";

    @Override
    public Boolean login(LoginRequest loginRequest) {
        try {
            ResponseEntity<LoginResponse> response = restTemplate.exchange(
                    url + "/login",
                    HttpMethod.POST,
                    new HttpEntity<>(loginRequest),
                    LoginResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                setPrinciple(response.getBody(), loginRequest.getPassword());

                Authentication authentication = AuthSessionUtil.getAuthentication();

                log.info("Name: {}", authentication.getName());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void setPrinciple(LoginResponse loginResponse, String password) {
        List<SimpleGrantedAuthority> authorities = loginResponse.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginResponse.getUsername(), password, authorities
        );

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}