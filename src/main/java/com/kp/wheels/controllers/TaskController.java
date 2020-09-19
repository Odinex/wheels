package com.kp.wheels.controllers;


import com.kp.wheels.entities.Task;
import com.kp.wheels.services.TaskService;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET, value = "/tasks/userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task[]> getTasksByUserId(@RequestHeader("userId") Long userId) {
        return new ResponseEntity<>(taskService.getTasksByUserId(userService.getUserById(userId)), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/all/userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task[]> getTasksByOwnerId(@RequestHeader("userId") Long userId) {
        return new ResponseEntity<>(taskService.getAllByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/tasks",consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveTask(@RequestBody Task task) {
        taskService.saveTask(task);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@RequestHeader("id") Long id) {
        taskService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
