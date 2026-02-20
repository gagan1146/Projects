package org.gagan.routematic_clone.service;

import org.gagan.routematic_clone.dto.loginDTO.UserLoginRequest;
import org.gagan.routematic_clone.dto.loginDTO.UserLoginResponse;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupRequest;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupResponse;
import org.gagan.routematic_clone.model.User;
import org.gagan.routematic_clone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<UserSignupResponse> signup(UserSignupRequest userSignupRequest) {
        User user = mapper.map(userSignupRequest, User.class);
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).build();
        }
        user.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));
        userRepository.save(user);
        UserSignupResponse userSignupResponse = mapper.map(user, UserSignupResponse.class);
        return ResponseEntity.ok(userSignupResponse);
    }

    public ResponseEntity<UserLoginResponse> login(UserLoginRequest userLoginRequest) {
        Optional<User> user = userRepository.findByEmail(userLoginRequest.getEmail());
        if (user.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        if (passwordEncoder.matches(userLoginRequest.getPassword(), user.get().getPassword())) {
            UserLoginResponse userLoginResponse = mapper.map(user, UserLoginResponse.class);
            return ResponseEntity.ok(userLoginResponse);
        }
        return ResponseEntity.status(401).build();
    }
}