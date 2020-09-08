package com.kp.wheels.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_id_sequence")
    @SequenceGenerator(name = "owner_id_sequence", sequenceName = "OWNER_ID_SEQ", allocationSize = 10)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;


    @Column(length = 1000)
    private String name;

    @Column
    private String email;

    public Owner() {
    }


    public Owner(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) &&
                Objects.equals(name, owner.name) &&
                Objects.equals(email, owner.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
