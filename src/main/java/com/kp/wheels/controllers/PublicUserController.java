package com.kp.wheels.controllers;

import com.kp.wheels.dto.UserDto;
import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/public/users")
final class PublicUserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> register(
            @RequestBody UserDto userDto)  {
        try {
            userService.signUp(userDto.getUsername(),userDto.getPassword(), userDto.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }   
        return login(userDto);
    }

    @PostMapping(value ="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> login(
            @RequestBody UserDto userDto) {
        Optional<User> user = userService
                .login(userDto.getUsername(), userDto.getPassword());
        return user.map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
