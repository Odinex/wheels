package com.kp.wheels.entities;


import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name", "user_id"})})
public class Wheel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wheel_id_sequence")
    @SequenceGenerator(name = "wheel_id_sequence", sequenceName = "WHEEL_ID_SEQ", allocationSize = 10)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column
    private String make;
    @Column
    private String model;
    @Column
    private String name;
    @Column
    private String variant;

    @ManyToOne
    private User user;

    public Wheel() {
    }

    public Wheel(String make, String model, String name, String variant, User user) {
        this.make = make;
        this.model = model;
        this.name = name;
        this.variant = variant;
        this.user = user;
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

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
