package com.kp.wheels.services.impl;

import com.kp.wheels.entities.Task;
import com.kp.wheels.entities.User;
import com.kp.wheels.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        return entityManager.createQuery("select w from Task where w.user = ?1", Task.class)
                .setParameter(1,entityManager.find(User.class,userId)).getResultList();
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
    public List<Task> getTasksByOwnerIdAndWheelId(Long userId, Long wheelId) {
        User user = entityManager.find(User.class, userId);
        List<Task> resultList = entityManager.createQuery("Select t from Task t Join t.wheel w where w.id = ?1 and w.user = ?2", Task.class)
                .setParameter(1, wheelId).setParameter( 2, user).getResultList();
        return resultList;
    }
}
