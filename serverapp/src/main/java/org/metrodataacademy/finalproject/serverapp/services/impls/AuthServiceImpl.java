package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.LoginRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.RegistrationRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.LoginResponse;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.RegistrationResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.Role;
import org.metrodataacademy.finalproject.serverapp.models.entities.User;
import org.metrodataacademy.finalproject.serverapp.repositories.RoleRepository;
import org.metrodataacademy.finalproject.serverapp.repositories.UserRepository;
import org.metrodataacademy.finalproject.serverapp.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            log.info("Trying to login user {}", loginRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            User users = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            log.info("Successfully sign in, user {}", userDetails.getUsername());
            return LoginResponse.builder()
                    .id(users.getId())
                    .email(users.getEmail())
                    .username(userDetails.getUsername())
                    .roles(roles)
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {
        try {
            log.info("Trying to registration new user with name: {}", registrationRequest.getName());
            if (userRepository.existsByEmail(registrationRequest.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
            } else if (userRepository.existsByPhone(registrationRequest.getPhone())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone already exists!");
            } else if (userRepository.existsByUsername(registrationRequest.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
            }

            User users = modelMapper.map(registrationRequest, User.class);

            List<Role> roles = Collections.singletonList(roleRepository.findById(1)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!")));

            users.setRoles(roles);
            users.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

            userRepository.save(users);
            log.info("Registration user successful, new user: {}", registrationRequest.getName());

            return RegistrationResponse.builder()
                    .id(users.getId())
                    .name(users.getName())
                    .email(users.getEmail())
                    .phone(users.getPhone())
                    .username(users.getUsername())
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }
}