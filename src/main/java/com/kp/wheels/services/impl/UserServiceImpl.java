package com.kp.wheels.services.impl;

import com.kp.wheels.entities.User;
import com.kp.wheels.exceptions.SignUpException;
import com.kp.wheels.services.UserCrudService;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserCrudService userCrudService;

    // BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public Optional<User> login(String username, String password) {
        // final String encryptedPassword = bCryptPasswordEncoder.encode(password);
        List<User> resultList = entityManager.createQuery("select c from User c where c.name = ?1 and c.password = ?2", User.class)
                .setParameter(1, username)
                .setParameter(2, password).getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            User user = resultList.get(0);
            userCrudService.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByToken(Long token) {
        Optional<User> user = userCrudService.find(token);
        return user;
    }

    @Override
    public void logout(User user) {
        userCrudService.removeUser(user);
    }

    @Override
    public void signUp(String username, String password, String email, Boolean mail, Boolean notifications, int days, int maxDaysBeforeUpcoming) throws SignUpException {
        if (entityManager.createQuery("select u from User u where u.name = ?1").setParameter(1, username).getResultList().isEmpty()) {

            User user = new User(username, password, email, mail, notifications, days,maxDaysBeforeUpcoming);
            //     final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

            user.setPassword(password);

            entityManager.persist(user);
        } else {
            throw new SignUpException("Username already used!");
        }
    }

    @Override
    public List<User> getUsersToBeNotified() {
        return entityManager.createQuery("Select u from User u where u.isSubscribedForMail = true " +
                "and (u.lastMailCheck is null or  u.lastMailCheck is not null and (u.lastMailCheck + u.daysBetweenNotifications )<= current_date)", User.class).getResultList();
    }

    @Override
    public User getUserById(Long userId) {

        return entityManager.find(User.class,userId);
    }

    @Override
    public void updateUserLastMailCheck(Date now, Long id) {
        User userById = getUserById(id);
        userById.setLastMailCheck(now);
        entityManager.persist(userById);
    }
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//         entityManager.createQuery("Select c FROM User c where c.email = :email",User.class).setParameter("email",s).getResultList();
//
//       return null;
//    }
}
