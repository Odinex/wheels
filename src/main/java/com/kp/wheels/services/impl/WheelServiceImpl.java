package com.kp.wheels.services.impl;

import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@EnableTransactionManagement
public class WheelServiceImpl implements WheelService {
    @Autowired
    EntityManager entityManager;


    @Override
    public List<Wheel> getAllWheels() {
        //TODO
        return entityManager.createQuery("Select w from Wheel ", Wheel.class).getResultList();
    }

    @Override
    public Wheel getWheelByName(String name) {
        return null;
    }

    @Override
    public void updateWheel(Wheel wheel) {

    }

    @Override
    public void saveWheel(Wheel wheel) {

    }
}
