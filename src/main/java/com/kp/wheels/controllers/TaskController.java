package com.kp.wheels.controllers;


import com.kp.wheels.entities.Task;
import com.kp.wheels.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;


    @RequestMapping(method = RequestMethod.GET, value = "/tasks/userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task[]> getTasksByUserId(@RequestHeader("userId") Long userId) {
        return new ResponseEntity<>(taskService.getTasksByUserId(userId), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/ids", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task[]> getTasksByOwnerId(@RequestHeader("userId") Long userId, @RequestHeader("wheelId") Long wheelId) {
        return new ResponseEntity<>(taskService.getTasksByUserIdAndWheelId(userId,wheelId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task>  getTaskByName(@RequestHeader("id") Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
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
