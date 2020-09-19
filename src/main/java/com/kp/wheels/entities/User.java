package com.kp.wheels.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kp.wheels.validation.ValidEmail;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;


    @Column(nullable = false)
    private String password;

    @Column
    @ValidEmail
    private String email;

    @Column(nullable = false)
    private Boolean isSubscribedForMail;

    @Column
    private Date lastMailCheck;

    @Column(nullable = false)
    private Boolean isSubscribedForNotifications;

    @Column
    private int daysBetweenNotifications =1;

    public User() {
    }

    public User(final String username,
                final String password, final String email, Boolean isSubscribedForMail, Boolean isSubscribedForNotifications, int daysBetweenNotifications) {
        super();
        this.name = requireNonNull(username);
        this.password = requireNonNull(password);
        this.email = requireNonNull(email);
        this.isSubscribedForMail = isSubscribedForMail;
        this.isSubscribedForNotifications = isSubscribedForNotifications;
        this.daysBetweenNotifications = daysBetweenNotifications;

    }

    @JsonCreator
    public User(@JsonProperty("id") final Long id,
                @JsonProperty("name") final String name,
                @JsonProperty("email") final String email,
                @JsonProperty("isSubscribedForMail") final Boolean isSubscribedForMail,
                @JsonProperty("isSubscribedForNotifications")final Boolean isSubscribedForNotifications,
                @JsonProperty("daysBetweenNotifications") int daysBetweenNotifications) {
        super();
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.email = requireNonNull(email);
        this.isSubscribedForMail = isSubscribedForMail;
        this.isSubscribedForNotifications = isSubscribedForNotifications;
        this.daysBetweenNotifications = daysBetweenNotifications;
    }

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
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastMailCheck() {
        return lastMailCheck;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isSubscribedForMail=" + isSubscribedForMail +
                ", lastMailCheck=" + lastMailCheck +
                ", isSubscribedForNotifications=" + isSubscribedForNotifications +
                ", daysBetweenNotifications=" + daysBetweenNotifications +
                '}';
    }

    public void setLastMailCheck(Date lastMailCheck) {
        this.lastMailCheck = lastMailCheck;
    }

    public Boolean getSubScribedForNotifications() {
        return isSubscribedForNotifications;
    }

    public void setSubScribedForNotifications(Boolean subScribedForNotifications) {
        isSubscribedForNotifications = subScribedForNotifications;
    }

    public int getDaysBetweenNotifications() {
        return daysBetweenNotifications;
    }

    public void setDaysBetweenNotifications(int daysBetweenNotifications) {
        this.daysBetweenNotifications = daysBetweenNotifications;
    }

    public Boolean getSubscribedForMail() {
        return isSubscribedForMail;
    }

    public void setSubscribedForMail(Boolean subscribedForMail) {
        isSubscribedForMail = subscribedForMail;
    }
}
