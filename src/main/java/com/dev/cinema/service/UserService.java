package com.dev.cinema.service;

import com.dev.cinema.model.User;

public interface UserService {

    User add(User user);

    User get(Long userId);

    User findByEmail(String email);
}
