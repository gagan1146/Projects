package org.gagan.intern_assignment_backend.controller;


import org.gagan.intern_assignment_backend.entity.User;
import org.gagan.intern_assignment_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        return userService.signup(user);
    }
}
