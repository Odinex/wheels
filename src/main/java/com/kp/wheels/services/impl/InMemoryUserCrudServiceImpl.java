package com.kp.wheels.services.impl;

import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@Transactional
public class InMemoryUserCrudServiceImpl implements UserCrudService {
    Map<Long, User> users = new HashMap<>();

    @Override
    public User save(final User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public Optional<User> find(final Long id) {
        return ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return users
                .values()
                .stream()
                .filter(u -> Objects.equals(username, u.getUsername()))
                .findFirst();
    }

    @Override
    public void removeUser(User user) {
        users.remove(user.getId());
        System.out.println("Removed user");
    }
}
