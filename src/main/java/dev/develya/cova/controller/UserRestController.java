package dev.develya.cova.controller;

import dev.develya.cova.model.User;
import dev.develya.cova.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        // Validate user input
        if (bindingResult.hasErrors()) {
            // Extract field errors
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            // Return field validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use.");
        }

        // Set registration date
        user.setRegistrationDate(LocalDateTime.now());

        // Set user as active
        user.setIsActive("TRUE");

        // Save user to database
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

}
