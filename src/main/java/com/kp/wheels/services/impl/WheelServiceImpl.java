package com.kp.wheels.services.impl;

import com.kp.wheels.entities.User;
import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class WheelServiceImpl implements WheelService {
    @Autowired
    EntityManager entityManager;


    @Override
    public Wheel getWheelByName(String name, Long userId) {
        User user = entityManager.find(User.class, userId);
        List<Wheel> resultList = entityManager.createQuery("Select w from Wheel w where w.name = ?1 and w.user = ?2", Wheel.class)
                .setParameter(1, name).setParameter( 2, user).getResultList();

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
    public void updateWheel(Wheel detachedWheel) {
        Wheel wheel = entityManager.find(Wheel.class, detachedWheel.getId());
        wheel.setMake(detachedWheel.getMake());
        wheel.setModel(detachedWheel.getModel());
        wheel.setName(detachedWheel.getName());
        entityManager.persist(wheel);
    }

    @Override
    public void saveWheel(Wheel detachedWheel) {
        User user = entityManager.find(User.class, detachedWheel.getUser().getId());
        Wheel newWheel = new Wheel(detachedWheel.getMake(),detachedWheel.getModel(),
                detachedWheel.getName(),detachedWheel.getVariant(),user);

        entityManager.persist(newWheel);

    }

    @Override
    public List<Wheel> getWheelsByUserId(Long userId) {
        return entityManager.createQuery("select w from Wheel w where w.user = ?1", Wheel.class).setParameter(1,entityManager.find(User.class,userId)).getResultList();
    }

    @Override
    public void delete(Long id) {
        Wheel wheel = entityManager.find(Wheel.class, id);
        entityManager.remove(wheel);
    }
}
