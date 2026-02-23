package org.gagan.intern_assignment_backend.service;

import org.gagan.intern_assignment_backend.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public ResponseEntity<User>signup(User user){
        return new  ResponseEntity<>(user, HttpStatus.OK);
    }
}
