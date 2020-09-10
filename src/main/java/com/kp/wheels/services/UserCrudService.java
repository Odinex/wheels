package com.kp.wheels.services;

import com.kp.wheels.entities.User;

import java.util.Optional;

public interface UserCrudService {

    User save(User user);

    Optional<User> find(Long id);

    Optional<User> findByUsername(String username);

    void removeUser(User user);
}
