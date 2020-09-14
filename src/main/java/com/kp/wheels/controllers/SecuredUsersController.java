package com.kp.wheels.controllers;

import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
final class SecuredUsersController {
    @Autowired
    UserService authentication;

    @GetMapping("/current")
    User getCurrent(@RequestParam("token") Long token) {
        return authentication.findByToken(token).get();
    }

    @GetMapping("/logout")
    boolean logout(@RequestParam("token") Long token) {
        try {
            authentication.logout(authentication.findByToken(token).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
