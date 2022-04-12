package com.standart.crudjwt.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.standart.crudjwt.model.MyUser;
import com.standart.crudjwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final PasswordEncoder encoder;

    public LoginController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MyUser user) {

        if (encoder.matches(user.getPassword(),
                userRepository.findByUserName(user.getUserName()).get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
