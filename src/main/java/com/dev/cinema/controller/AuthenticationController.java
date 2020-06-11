package com.dev.cinema.controller;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private AuthenticationService authenticationService;
    private UserMapper userMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        return userMapper.getUserResponseDto(authenticationService.register(
                userRequestDto.getEmail(), userRequestDto.getPassword()));
    }
}
