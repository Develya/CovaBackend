package dev.develya.cova.controller;

import dev.develya.cova.model.User;
import dev.develya.cova.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<User> all() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use.");

        // Set registration date
        user.setRegistrationDate(LocalDateTime.now());

        // Set user as active
        user.setIsActive("TRUE");

        // Save user to database
        userRepository.save(user);
        //ArRianNe avec deux N et deux R
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }
}
