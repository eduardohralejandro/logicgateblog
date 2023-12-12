package com.main.logicgate.controller;

import com.main.logicgate.common.enums.UserRole;
import com.main.logicgate.model.ArticleModel;
import com.main.logicgate.model.UserModel;
import com.main.logicgate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    record NewUserRequest(
            String name,
            String lastName,
            String email,
            String password,
            String photo,
            UserRole userRole,
            Date dateCreated,
            Date dateUpdated,
            List<ArticleModel> articles
    ) {}

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody NewUserRequest request) {
        try {
            UserModel newUser = new UserModel();
            String hashedPassword = passwordEncoder.encode(request.password);


            newUser.setName(request.name);
            newUser.setLastName(request.lastName);
            newUser.setEmail(request.email);
            newUser.setPhoto(request.photo);
            newUser.setUserRole(request.userRole);
            newUser.setDateCreated(request.dateUpdated);
            newUser.setArticles(request.articles);
            newUser.setPassword(hashedPassword);

            userRepository.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user");
        }
    }
}
