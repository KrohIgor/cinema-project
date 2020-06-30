package com.dev.cinema.controller;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/by-email")
    public UserResponseDto getByEmail(Authentication authentication) {
        return userMapper.getUserResponseDto(userService.findByEmail(authentication.getName()));
    }
}
