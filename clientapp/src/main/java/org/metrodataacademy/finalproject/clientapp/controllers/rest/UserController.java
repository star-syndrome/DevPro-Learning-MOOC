package org.metrodataacademy.finalproject.clientapp.controllers.rest;

import org.metrodataacademy.finalproject.clientapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(
        path = "/admin/total-users",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getTotalUsers() {
        return ResponseEntity.ok().body(userService.countAllUsers());
    }
}