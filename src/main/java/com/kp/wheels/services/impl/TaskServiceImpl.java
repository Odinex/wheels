package com.kp.wheels.services.impl;

import com.kp.wheels.entities.Task;
import com.kp.wheels.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.security.acl.Owner;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Task> getTasksByOwnerId(Long ownerId) {
        return entityManager.createQuery("select w from Task where w.owner = ?1", Task.class).setParameter(1,entityManager.find(Owner.class,ownerId)).getResultList();
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public void updateTask(Task task) {
        saveTask( task);
    }

    @Override
    public void saveTask(Task task) {
        entityManager.persist(task);
    }


    @Override
    public List<Task> getTasksByOwnerIdAndWheelId(Long ownerId, Long wheelId) {
        Owner owner = entityManager.find(Owner.class, ownerId);
        List<Task> resultList = entityManager.createQuery("Select t from Task t Join t.wheel w where w.id = ?1 and w.owner = ?2", Task.class)
                .setParameter(1, wheelId).setParameter( 2, owner).getResultList();
        return resultList;
    }
}
