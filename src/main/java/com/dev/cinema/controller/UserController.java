package com.dev.cinema.controller;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/byemail")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return userMapper.getUserResponseDto(userService.findByEmail(email));
    }
}
