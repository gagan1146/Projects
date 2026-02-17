package org.gagan.login.controllers;

import org.gagan.login.models.User;
import org.gagan.login.repository.UserRepository;
import org.gagan.login.requests.LoginRequest;
import org.gagan.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    public UserService userService;
    public UserRepository userRepository;
    @Autowired
    public UserController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @PostMapping("/register")
    @CrossOrigin("http://localhost:4200")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }
}
