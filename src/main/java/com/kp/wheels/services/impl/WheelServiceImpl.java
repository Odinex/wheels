package com.kp.wheels.services.impl;

import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.security.acl.Owner;
import java.util.List;

@Service
@Transactional
public class WheelServiceImpl implements WheelService {
    @Autowired
    EntityManager entityManager;


    @Override
    public Wheel getWheelByName(String name, Long ownerId) {
        Owner owner = entityManager.find(Owner.class, ownerId);
        List<Wheel> resultList = entityManager.createQuery("Select w from Wheel w where w.name = ?1 and w.owner = ?2", Wheel.class)
                .setParameter(1, name).setParameter( 2, owner).getResultList();

        Wheel wheel = null;
        if(resultList != null && !resultList.isEmpty()) {
            wheel = resultList.get(0);
        }
        return wheel;
    }

    @Override
    public Wheel getWheelById(Long id) {
        return entityManager.find(Wheel.class, id);
    }

    @Override
    public void updateWheel(Wheel wheel) {
        saveWheel(wheel);

    }

    @Override
    public void saveWheel(Wheel wheel) {
        entityManager.persist(wheel);

    }

    @Override
    public List<Wheel> getWheelsByOwnerId(Long ownerId) {
        return entityManager.createQuery("select w from Wheel where w.owner = ?1", Wheel.class).setParameter(1,entityManager.find(Owner.class,ownerId)).getResultList();
    }
}
