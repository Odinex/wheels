package com.kp.wheels.controllers;

import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/users")
final class PublicUserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    User register(
            @RequestParam(value = "username") final String username,
            @RequestParam("password") final String password) throws Exception {
        userService.signUp(username, password);


        User login = login(username, password);
        return login;
    }

    @PostMapping("/login")
    User login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        return userService
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
