package com.kp.wheels.controllers;

import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/users")
final class PublicUserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    Long register(
            @RequestHeader(value = "username") final String username,
            @RequestHeader("password") final String password) {
        userService.signUp(username, password);


        Long login = login(username, password);
        return login;
    }

    @PostMapping("/login")
    Long login(
            @RequestHeader("username") final String username,
            @RequestHeader("password") final String password) {
        return userService
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
