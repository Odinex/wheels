package com.kp.wheels.scheduling;


import com.kp.wheels.entities.User;
import com.kp.wheels.services.EmailService;
import com.kp.wheels.services.TaskService;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class EmailSchedulerTasks {
    @Autowired
    EmailService emailService;
    @Autowired
    TaskService taskService;
    @Autowired
    UserService userService;

    @PostConstruct()
    public void onStartUp() {
        sendEmailNotifications();
    }

    //@Scheduled(cron = "0 10 * * *")
    @Scheduled(fixedDelay = 50000)
    public void sendEmailNotifications() {
        List<User> usersToBeNotified = userService.getUsersToBeNotified();
        for (User user : usersToBeNotified) {
            Calendar calendar = Calendar.getInstance();
            Date now = new Date();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, user.getMaxDaysBeforeUpcoming());
            int countOfUpcomingTasks = taskService.getCountOfUpcomingTasks(user, calendar.getTime());
            if(countOfUpcomingTasks > 0) {
                emailService.sendSimpleMessage(user.getEmail(), "Reminder for upcoming tasks for your cars",
                        "Dear " + user.getName() + ",\n\nThere are " + countOfUpcomingTasks + " tasks that are upcoming for your cars. " +
                                "Please check your Wheels Diary for more information.\n\nBest wishes,\nWheels Diary Team");
            }
            calendar.setTime(now);
            userService.updateUserLastMailCheck(now, user.getId());
        }

    }
}
