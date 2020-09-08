package com.kp.wheels.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wheel {
    @Column
    private String make;
    @Column
    private String model;
    @Id
    private String name;
    @Column
    private String variant;

    public Wheel() {
    }

    public Wheel(String make, String model, String name, String variant) {

        this.make = make;
        this.model = model;
        this.name = name;
        this.variant = variant;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public String getVariant() {
        return variant;
    }
}
