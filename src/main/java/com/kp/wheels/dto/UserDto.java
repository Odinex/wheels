package com.kp.wheels.dto;

public class UserDto {
    private String username;
    private String password;
    private String email;
    private Boolean isSubscribedForMail = true;
    private Boolean isSubscribedForNotifications = true;
    private int daysBetweenNotifications = 1;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSubscribedForMail() {
        return isSubscribedForMail;
    }

    public void setSubscribedForMail(Boolean subscribedForMail) {
        isSubscribedForMail = subscribedForMail;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
