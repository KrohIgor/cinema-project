package com.dev.cinema.controller;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.dto.UserRegistrationDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return userMapper.getUserResponseDto(authenticationService.register(
                userRegistrationDto.getEmail(), userRegistrationDto.getPassword()));
    }
}
