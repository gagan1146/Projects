package org.gagan.authentication.service;

import org.gagan.authentication.model.UserModel;
import org.gagan.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findById(email);
    }

    public ResponseEntity<UserModel> signup(String email, String name, String password) {
        Optional<UserModel> findUser = findByEmail(email);
        if (findUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        String encodedPassword = passwordEncoder.encode(password);
        UserModel user = new UserModel(name, email, encodedPassword);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    public ResponseEntity<UserModel> login(String email, String rawPassword) {
        Optional<UserModel> userOpt = findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        UserModel user = userOpt.get();
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
