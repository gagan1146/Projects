package org.gagan.routematic_clone.service;

import org.gagan.routematic_clone.dto.loginDTO.UserLoginRequest;
import org.gagan.routematic_clone.dto.loginDTO.UserLoginResponse;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupRequest;
import org.gagan.routematic_clone.dto.signupDTO.UserSignupResponse;
import org.gagan.routematic_clone.dto.userDTO.UserResponse;
import org.gagan.routematic_clone.dto.userDTO.UserUpdateRequest;
import org.gagan.routematic_clone.model.User;
import org.gagan.routematic_clone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

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
            UserLoginResponse userLoginResponse = mapper.map(user.get(), UserLoginResponse.class);
            return ResponseEntity.ok(userLoginResponse);
        }
        return ResponseEntity.status(401).build();
    }

    public ResponseEntity<UserResponse> updateUser(UUID userId, UserUpdateRequest updateRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        user.setUsername(updateRequest.getUsername());
        user.setPhoneNumber(updateRequest.getPhoneNumber());
        user.setDepartment(updateRequest.getDepartment());
        user.setRole(updateRequest.getRole());

        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }

        userRepository.save(user);
        UserResponse userResponse = mapper.map(user, UserResponse.class);
        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }

    public ResponseEntity<UserResponse> getUserById(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserResponse userResponse = mapper.map(userOptional.get(), UserResponse.class);
        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<Void> deleteUser(UUID userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}