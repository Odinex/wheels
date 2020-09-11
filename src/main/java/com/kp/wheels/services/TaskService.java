package com.kp.wheels.services;

import com.kp.wheels.entities.Task;
import com.kp.wheels.entities.Wheel;

import java.util.List;

public interface TaskService {

    Task getTaskById(Long id);

    void updateTask(Task wheel);

    void saveTask(Task wheel);

    List<Task> getTasksByUserId(Long ownerId);
    List<Task> getTasksByOwnerIdAndWheelId(Long ownerId,Long wheelId);
}
