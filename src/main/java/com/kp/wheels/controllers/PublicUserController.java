package com.kp.wheels.controllers;

import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserCrudService;
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

    @Autowired
    UserCrudService userCrudService;

    @PostMapping("/register")
    Long register(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        User user = new User(username, password);
        userService.singUp(user);
        userCrudService.save(user);

        return login(username, password);
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
