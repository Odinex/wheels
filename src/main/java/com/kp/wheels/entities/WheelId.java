package com.kp.wheels.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WheelId implements Serializable {

    @Column
    private String name;

    @Column
    private Long ownerId;

    public WheelId(String name, Long ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }

    public WheelId() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelId wheelId = (WheelId) o;
        return Objects.equals(name, wheelId.name) &&
                Objects.equals(ownerId, wheelId.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ownerId);
    }
}
