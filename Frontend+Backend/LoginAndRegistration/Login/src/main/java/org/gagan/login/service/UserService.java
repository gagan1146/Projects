package org.gagan.login.service;

import org.gagan.login.models.User;
import org.gagan.login.repository.UserRepository;
import org.gagan.login.requests.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public Boolean loginUser(LoginRequest loginRequest){
        Optional<User> optionalUser = userRepository.findById(loginRequest.getUserId());
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();

            String password = user1.getPassword();
            return password.equals(loginRequest.getPassword());
        }

        return false;
    }
}
