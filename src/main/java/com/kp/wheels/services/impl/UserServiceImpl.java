package com.kp.wheels.services.impl;

import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserCrudService;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserCrudService userCrudService;


    @Override
    public Optional<Long> login(String username, String password) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(password);
        List<User> resultList = entityManager.createQuery("select c from User c where c.username = ?1 and c.password = ?2", User.class)
                .setParameter(1, username)
                .setParameter(2, encryptedPassword).getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            User user = resultList.get(0);
            userCrudService.save(user);
            return Optional.of(user.getId());
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
    public void signUp(String username, String password) {

        User user = new User(username, password);
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        entityManager.persist(user);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        entityManager.createQuery("Select c FROM User c where c.email = :email", User.class).setParameter("email", s).getResultList();

        return null;
    }
}
