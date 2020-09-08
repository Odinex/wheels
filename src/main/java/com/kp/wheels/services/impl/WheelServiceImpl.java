package com.kp.wheels.services.impl;

import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.WheelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service
@EnableTransactionManagement
public class WheelServiceImpl implements WheelService {
    @Override
    public  getAllWheels() {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<Wheel> getWheelByName(String name) {
        return null;
    }
}
