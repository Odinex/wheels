package com.kp.wheels.controllers;

import com.kp.wheels.dto.UserDto;
import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/users")
final class PublicUserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    User register(
            @RequestBody UserDto userDto) throws Exception {
        userService.signUp(userDto.getUsername(),userDto.getPassword());


        User login = login(userDto);
        System.out.println("login result " + login);
        return login;
    }

    @PostMapping(value ="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    User login(
            @RequestBody UserDto userDto) {
        return userService
                .login(userDto.getUsername(),userDto.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
