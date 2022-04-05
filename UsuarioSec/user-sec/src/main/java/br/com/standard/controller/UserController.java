package br.com.standard.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.standard.model.UserModel;
import br.com.standard.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordEncoder encoder;

    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> list = repository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<UserModel>>(list, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> saveProduto(@RequestBody @Valid UserModel userModel) {
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        return new ResponseEntity<UserModel>((UserModel) repository.save(userModel), HttpStatus.CREATED);
    }
    /*
     * @GetMapping("/teste")
     * public ResponseEntity<Boolean> validarSenha(@RequestParam String username,
     * 
     * @RequestParam String password) {
     * 
     * Optional<UserModel> optUser = repository.findByUserName(username);
     * if (optUser.isEmpty()) {
     * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
     * }
     * 
     * boolean valid = false;
     * 
     * UserModel user = optUser.get();
     * valid = encoder.matches(password, user.getPassword());
     * 
     * HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
     * 
     * return null;
     * 
     * }
     */
}
