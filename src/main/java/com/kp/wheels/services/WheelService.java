package com.kp.wheels.services;

import com.kp.wheels.entities.Wheel;

import java.util.List;

public interface WheelService {

    Wheel getWheelByName(String name, Long ownerId);

    Wheel getWheelById(Long id);

    void updateWheel(Wheel wheel);

    void saveWheel(Wheel wheel);

    List<Wheel> getWheelsByOwnerId(Long ownerId);
}
