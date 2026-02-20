package org.gagan.routematic_clone.controller;

import org.gagan.routematic_clone.dto.loginDTO.UserLoginRequest;
import org.gagan.routematic_clone.dto.loginDTO.UserLoginResponse;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupRequest;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupResponse;
import org.gagan.routematic_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest userSignupRequest){
        return userService.signup(userSignupRequest);
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }
}
