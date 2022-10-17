package br.ufac.productmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.productmanager.model.User;
import br.ufac.productmanager.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController implements ICrudController<User> {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        List<User> registers = service.getAll();
        return new ResponseEntity<>(registers, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        User register = service.getById(id);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username") String username){
        User user = service.getByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

     @PostMapping("/")
     public ResponseEntity<User> insert(@RequestBody User objeto) {
         User register = service.save(objeto);
         return new ResponseEntity<>(register, HttpStatus.CREATED);
     }

    // @Override
    // @PutMapping("/")
    // public ResponseEntity<User> update(@RequestBody User objeto) {
    //     User register = service.save(objeto);
    //     return new ResponseEntity<>(register, HttpStatus.OK);
    // }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
