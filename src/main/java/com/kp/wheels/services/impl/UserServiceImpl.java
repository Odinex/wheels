package com.kp.wheels.services.impl;

import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    EntityManager entityManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void singUp(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        entityManager.persist(user);
    }

    @Override
    public Optional<String> login(String username, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public void logout(User user) {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
         entityManager.createQuery("Select c FROM User c where c.email = :email",User.class).setParameter("email",s).getResultList();

       return null;
    }
}
