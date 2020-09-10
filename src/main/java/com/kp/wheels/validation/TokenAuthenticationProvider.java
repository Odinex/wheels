package com.kp.wheels.validation;

import com.kp.wheels.entities.User;
import com.kp.wheels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    UserService auth;

    @Override
    protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
        // Nothing to do
    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {

        //Improve this line:
        String password = authentication.getCredentials().toString();
        // Invoke your webservice here
        Optional<Long> login = auth.login(username, password);
        if(login.isPresent()) {
            Long token = login.get();
            Optional<User> user = auth.findByToken(token);
            if(user.isPresent()) {
                return user.get();
            }
        }
        return null;
    }
}
