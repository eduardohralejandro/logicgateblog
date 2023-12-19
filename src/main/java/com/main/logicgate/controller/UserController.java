package com.main.logicgate.controller;

import com.main.logicgate.model.UserModel;
import com.main.logicgate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }
}
