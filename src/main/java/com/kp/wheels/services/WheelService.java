package com.kp.wheels.services;

import com.kp.wheels.entities.Wheel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WheelService {
   List<Wheel> getAllWheels();

    ResponseEntity<Wheel> getWheelByName(String name);
}
