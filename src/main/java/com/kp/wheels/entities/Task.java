package com.kp.wheels.entities;


import com.kp.wheels.enums.TaskTypeEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_sequence")
    @SequenceGenerator(name = "task_id_sequence", sequenceName = "TASK_ID_SEQ", allocationSize = 10)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private Date dateCreated;
    @Column
    private Date dateScheduled;
    @Column
    private TaskTypeEnum taskType;
    @Column
    private String otherTaskType;
    @Column
    private String details;
    @ManyToOne
    private Wheel wheel;

    public Task() {
    }

    public Task(Date dateCreated, Date dateScheduled, TaskTypeEnum taskType, String otherTaskType, String details, Wheel wheel) {
        this.dateCreated = dateCreated;
        this.dateScheduled = dateScheduled;
        this.taskType = taskType;
        this.otherTaskType = otherTaskType;
        this.details = details;
        this.wheel = wheel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public TaskTypeEnum getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskTypeEnum taskType) {
        this.taskType = taskType;
    }

    public String getOtherTaskType() {
        return otherTaskType;
    }

    public void setOtherTaskType(String otherTaskType) {
        this.otherTaskType = otherTaskType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }
}
