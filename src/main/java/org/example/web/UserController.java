package org.example.web;

import org.example.model.entity.User;
import org.example.service.*;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User> {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Service<User> getService() {
        return userService;
    }
}