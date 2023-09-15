package com.training.simplecrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.simplecrud.model.Users;
import com.training.simplecrud.repository.UsersRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<Users> getAllUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Users createUser(@Valid @RequestBody Users user) {
        return usersRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id,
    @Valid @RequestBody Users updateUser) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setUsername(updateUser.getUsername());
            user.setPassword(updateUser.getPassword());
            user.setEmail(updateUser.getEmail());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
