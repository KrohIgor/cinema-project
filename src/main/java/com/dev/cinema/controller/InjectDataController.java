package com.dev.cinema.controller;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public InjectDataController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void injectRolesToDb() {
        roleService.add(Role.of("USER"));
        User admin = new User();
        admin.setEmail("admin@ukr.net");
        admin.setPassword("4321");
        Role adminRole = roleService.add(Role.of("ADMIN"));
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
    }
}
