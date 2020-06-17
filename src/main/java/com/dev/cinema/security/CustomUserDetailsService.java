package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByEmail(userName);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(userName);
            userBuilder.password(user.getPassword());
            userBuilder.roles(user.getRoles().stream()
                    .map(Role::getRoleName)
                    .map(Enum::name)
                    .toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User's name not found!");
        }
        return userBuilder.build();
    }
}
