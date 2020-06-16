package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService;
    private ShoppingCartService shoppingCartService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);
        if (userFromDB != null && passwordEncoder.encode(password)
                .equals(userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
