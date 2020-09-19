package com.kp.wheels.services;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
