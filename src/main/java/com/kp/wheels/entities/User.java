package com.kp.wheels.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kp.wheels.enums.UserRole;

import javax.persistence.*;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;


    @Column
    private String password;

    @Column
    private UserRole userRole = UserRole.USER;

    public User(String name, String password, UserRole userRole) {
        this.name = name;
        this.password = password;
        this.userRole = userRole;
    }

    public User() {
    }
    @JsonCreator
    public User(@JsonProperty("username") final String username,
                @JsonProperty("password") final String password) {
        super();
        this.name = requireNonNull(username);
        this.password = requireNonNull(password);
    }
//    @JsonIgnore
//    public Collection<GrantedAuthority> getAuthorities() {
//        return new ArrayList<>();
//    }

    @JsonIgnore
  //  @Override
    public String getPassword() {
        return password;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  password, userRole);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
