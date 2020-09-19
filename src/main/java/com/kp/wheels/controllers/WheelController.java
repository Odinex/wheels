package com.kp.wheels.controllers;

import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class WheelController {
    @Autowired
    WheelService wheelService;


    @RequestMapping(method = RequestMethod.GET, value = "/wheels/userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wheel>> getWheelsByUserId(@RequestHeader("userId") Long userId) {
        return new ResponseEntity<>(wheelService.getWheelsByUserId(userId), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/wheels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateWheel(@RequestBody Wheel wheel) {
        wheelService.updateWheel(wheel);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/wheels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveWheel(@RequestBody Wheel wheel) {
        wheelService.saveWheel(wheel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/wheels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteWheel(@RequestHeader("id") Long id) {
        wheelService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
