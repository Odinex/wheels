package com.kp.wheels.controllers;

import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class WheelController {
    @Autowired
    WheelService wheelService;


    @RequestMapping(method = RequestMethod.GET, value = "/wheels/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wheel>> getWheelsByOwnerId(@RequestHeader("ownerId") Long ownerId) {
        return new ResponseEntity<>(wheelService.getWheelsByOwnerId(ownerId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/wheels/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wheel>  getWheelByName(@RequestHeader("name") String name) {
        return new ResponseEntity<>(wheelService.getWheelByName(name), HttpStatus.OK);
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

}
