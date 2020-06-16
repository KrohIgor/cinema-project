package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User add(User user) {
        String encoder = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
        return userDao.add(user);
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }
}
