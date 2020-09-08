package com.kp.wheels.services.impl;

import com.kp.wheels.entities.Task;
import com.kp.wheels.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    EntityManager entityManager;

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public void updateTask(Task wheel) {

    }

    @Override
    public void saveTask(Task wheel) {

    }

    @Override
    public List<Task> getTasksByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public List<Task> getTasksByOwnerIdAndWheelId(Long ownerId, Long wheelId) {
        return null;
    }
}
