package org.metrodataacademy.finalproject.serverapp.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.ChangePasswordRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.requests.UpdateUserProfileRequest;
import org.metrodataacademy.finalproject.serverapp.models.dtos.responses.UserResponse;
import org.metrodataacademy.finalproject.serverapp.models.entities.User;
import org.metrodataacademy.finalproject.serverapp.repositories.UserRepository;
import org.metrodataacademy.finalproject.serverapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getUserProfile() {
        try {
            log.info("Getting information details from user");
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            return mapToUserResponse(user);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public UserResponse updateUserProfile(UpdateUserProfileRequest updateUserProfileRequest) {
        try {
            log.info("Attempting to modify the user data for {}!", updateUserProfileRequest.getName());
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            if (userRepository.existsByEmailAndNotId(updateUserProfileRequest.getEmail(), user.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
            }

            if (userRepository.existsByPhoneAndNotId(updateUserProfileRequest.getPhone(), user.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number already exists!");
            }

            user.setName(updateUserProfileRequest.getName());
            user.setEmail(updateUserProfileRequest.getEmail());
            user.setPhone(updateUserProfileRequest.getPhone());
            user.setCity(updateUserProfileRequest.getCity());
            user.setCountry(updateUserProfileRequest.getCountry());
            userRepository.save(user);

            log.info("Successfully modified {}'s data!", user.getName());
            return mapToUserResponse(user);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public UserResponse changePasswordUser(ChangePasswordRequest changePasswordRequest) {
        try {
            log.info("Attempting to modify the password");
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

            if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password");
            }

            if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password");
            }

            user.setPassword(passwordEncoder.encode(changePasswordRequest.getConfirmationPassword()));
            userRepository.save(user);
            log.info("Successfully changed password!");

            return mapToUserResponse(user);
        } catch (Exception e) {
            System.out.println("Error: {}" + e.getMessage());
            throw e;
        }
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .city(user.getCity())
                .country(user.getCountry())
                .username(user.getUsername())
                .build();
    }
}