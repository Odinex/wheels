package com.kp.wheels.services;

import com.kp.wheels.entities.Task;

public interface TaskService {

    Task getTaskById(Long id);

    void updateTask(Task wheel);

    void saveTask(Task wheel);

    Task[] getTasksByUserId(Long ownerId);
    Task[] getTasksByUserIdAndWheelId(Long ownerId, Long wheelId);
}
