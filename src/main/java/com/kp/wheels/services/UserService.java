package com.kp.wheels.services;

import com.kp.wheels.entities.User;

import java.util.Optional;

public interface UserService {//extends UserDetailsService {

    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @param username
     * @param password
     * @return an {@link Optional} of a user when login succeeds
     */
    Optional<User> login(String username, String password);

    /**
     * Finds a user by its dao-key.
     *
     * @param token user dao key
     * @return
     */
    Optional<User> findByToken(Long token);

    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    void logout(User user);


    void signUp(String username, String password, String email, Boolean mail, Boolean notifications, int days) throws Exception;
}
