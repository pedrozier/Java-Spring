package com.standart.crudjwt.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.standart.crudjwt.model.MyUser;
import com.standart.crudjwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@Secured("ADMIN")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final PasswordEncoder encoder;

    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.userRepository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/save")
    public ResponseEntity<MyUser> saveUser(@RequestBody @Valid MyUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
        user.setPassword("[PROTECTED]");
        return new ResponseEntity<MyUser>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<MyUser> updateUser(@PathVariable(value = "id") long id, @RequestBody @Valid MyUser user) {
        Optional<MyUser> userO = userRepository.findById(id);
        if (!userO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            user.setId(userO.get().getId());
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<MyUser>(user, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long id, @RequestBody @Valid MyUser user) {
        Optional<MyUser> userO = userRepository.findById(id);
        if (!userO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userRepository.delete(userO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<MyUser> getOneUser(@PathVariable(value = "id") long id) {

        Optional<MyUser> userO = userRepository.findById(id);

        if (!userO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            MyUser user = userO.get();
            user.setPassword("[PROTECTED]");
            return new ResponseEntity<MyUser>(user, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (MyUser user : userList) {
                user.setPassword("[PROTECTED]");
            }
            return new ResponseEntity<List<MyUser>>(userList, HttpStatus.OK);
        }
    }

}
