package com.kp.wheels.controllers;

import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/users")
final class PublicUserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    Long register(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        userService.signUp(username, password);


        Long login = login(username, password);
        return login;
    }

    @PostMapping("/login")
    Long login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        return userService
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
