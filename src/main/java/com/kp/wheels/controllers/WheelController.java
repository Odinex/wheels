package com.kp.wheels.controllers;

import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class WheelController {
    @Autowired
    WheelService wheelService;

    @RequestMapping(method = RequestMethod.GET, value = "/wheels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wheel>>  getAllWheels() {
        return new ResponseEntity<List<Wheel>>(wheelService.getAllWheels());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/wheels/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wheel>  getWheelByName(@RequestHeader("name") String name) {
        return wheelService.getWheelByName(name);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/wheels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wheel>  updateWheel(@RequestBody Wheel wheel) {
        return wheelService.updateWheel(wheel);
    }

}
