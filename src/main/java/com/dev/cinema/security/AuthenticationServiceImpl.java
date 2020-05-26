package com.dev.cinema.security;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);
        if (userFromDB != null && HashUtil.hashPassword(password, userFromDB.getSalt())
                .equals(userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashPassword = HashUtil.hashPassword(password, salt);
        user.setEmail(email);
        user.setPassword(hashPassword);
        User userFromDB = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDB);
        return userFromDB;
    }
}
