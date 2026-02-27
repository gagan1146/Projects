package org.gagan.login.service;

import org.gagan.login.models.User;
import org.gagan.login.repository.UserRepository;
import org.gagan.login.requests.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    public UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getEmail());
        if (existingUser.isPresent()) {
            log.warn("User already exists with ID: {}", user.getEmail());
            throw new IllegalArgumentException("User already exists with ID: " + user.getEmail());
        }
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getEmail());
        return savedUser;
    }

    public ResponseEntity<User> loginUser(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findById(loginRequest.getUserId());
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            String password = user1.getPassword();
            if (password.equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(user1);
            } else {
                log.warn("Invalid password for user {}", loginRequest.getUserId());
                return ResponseEntity.status(401).build();
            }
        } else {
            log.warn("User not found: {}", loginRequest.getUserId());
            return ResponseEntity.notFound().build();
        }
    }
}
