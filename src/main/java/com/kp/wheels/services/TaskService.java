package com.kp.wheels.services;

import com.kp.wheels.entities.Task;
import com.kp.wheels.entities.User;

import java.util.Date;

public interface TaskService {

    int getCountOfUpcomingTasks(User user, Date maxDate);

    Task getTaskById(Long id);

    void updateTask(Task wheel);

    void saveTask(Task wheel);

    Task[] getTasksByUserId(User o);
    Task[] getAllByUserId(Long ownerId);

    void delete(Long id);
}
