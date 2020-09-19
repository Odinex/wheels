package com.kp.wheels.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String taskType;
    @Column
    private String details;
    @Column
    private Boolean isCompleted;
    @Column
    private Double price;
    @Column
    private Integer travelledKmWhenCompleted;
    @ManyToOne
    private Wheel wheel;

    public Task() {
    }

    public Task(Date dateCreated, Date dateScheduled, String taskType, String details, Boolean isCompleted, Double price, Integer travelledKmWhenCompleted, Wheel wheel) {
        this.dateCreated = dateCreated;
        this.dateScheduled = dateScheduled;
        this.taskType = taskType;
        this.details = details;
        this.isCompleted = isCompleted;
        this.price = price;
        this.travelledKmWhenCompleted = travelledKmWhenCompleted;
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

    @JsonProperty("isCompleted")
    public Boolean getCompleted() {
        return isCompleted;
    }
    @JsonProperty("isCompleted")
    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTravelledKmWhenCompleted() {
        return travelledKmWhenCompleted;
    }

    public void setTravelledKmWhenCompleted(Integer travelledKmWhenCompleted) {
        this.travelledKmWhenCompleted = travelledKmWhenCompleted;
    }

    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
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
