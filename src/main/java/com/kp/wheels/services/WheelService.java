package com.kp.wheels.services;

import com.kp.wheels.entities.Wheel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WheelService {

    Wheel getWheelByName(String name);

    void updateWheel(Wheel wheel);

    void saveWheel(Wheel wheel);

    List<Wheel> getWheelsByOwnerId(Long ownerId);
}
