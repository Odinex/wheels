package com.kp.wheels.services.impl;

import com.kp.wheels.entities.Task;
import com.kp.wheels.entities.User;
import com.kp.wheels.entities.Wheel;
import com.kp.wheels.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    EntityManager entityManager;

    @Override
    public Task[] getTasksByUserId(Long userId) {
        return entityManager.createQuery("select t from Task t join t.wheel w where w.user = ?1 " +
                "and t.dateScheduled >= current_date order by t.dateScheduled asc", Task.class)
                .setParameter(1, entityManager.find(User.class, userId)).getResultList().toArray(new Task[0]);
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public void updateTask(Task jsonTask) {
        Task task = entityManager.find(Task.class, jsonTask.getId());
        task.setDateScheduled(jsonTask.getDateScheduled());
        task.setDetails(jsonTask.getDetails());
        task.setCompleted(jsonTask.getCompleted());
        task.setPrice(jsonTask.getPrice());
        task.setTravelledKmWhenCompleted(jsonTask.getTravelledKmWhenCompleted());
        task.setTaskType(jsonTask.getTaskType());
        if (!Objects.equals(jsonTask.getWheel().getId(), task.getWheel().getId())) {
            Wheel wheel = entityManager.find(Wheel.class, jsonTask.getWheel().getId());
            task.setWheel(wheel);
        }
        entityManager.persist(task);
    }

    @Override
    public void saveTask(Task jsonTask) {
        Wheel wheel = entityManager.find(Wheel.class, jsonTask.getWheel().getId());
        Task newTask = new Task(new Date(), jsonTask.getDateScheduled(),
                jsonTask.getTaskType(), jsonTask.getDetails(), jsonTask.getCompleted(),
                jsonTask.getPrice(), jsonTask.getTravelledKmWhenCompleted(), wheel);

        entityManager.persist(newTask);
    }


    @Override
    public Task[] getAllByUserId(Long userId) {
        return entityManager.createQuery("select t from Task t join t.wheel w where w.user = ?1 " +
                " order by t.dateScheduled asc", Task.class)
                .setParameter(1, entityManager.find(User.class, userId)).getResultList().toArray(new Task[0]);
    }

    @Override
    public void delete(Long id) {
        Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
    }
}
