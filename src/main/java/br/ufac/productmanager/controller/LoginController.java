package br.ufac.productmanager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.User;
import br.ufac.productmanager.service.UserService;

@RestController
@RequestMapping("/user_info")
public class LoginController {

    private final UserService service;

    @Autowired
    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<User> getUsuario() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = service.getByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // @Override
    @PostMapping("/user/")
    public ResponseEntity<User> signUpUser(@RequestBody User object) {
        // User user = service.getByIdWithPassword(id);
        User userSaved = service.save(object, null);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }
    
}
