package org.gagan.routematic_clone.controller;

import org.gagan.routematic_clone.dto.loginDTO.UserLoginRequest;
import org.gagan.routematic_clone.dto.loginDTO.UserLoginResponse;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupRequest;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupResponse;
import org.gagan.routematic_clone.dto.userDTO.UserResponse;
import org.gagan.routematic_clone.dto.userDTO.UserUpdateRequest;
import org.gagan.routematic_clone.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest request) {
        return userService.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id,
                                                   @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}