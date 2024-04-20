package dev.develya.cova.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.develya.cova.model.User;
import dev.develya.cova.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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

    private Gson gson = new Gson();

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> all() {
        return userRepository.findAll();
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        try {
            user.setRegistrationDate(LocalDateTime.now());
            user.setIsActive("TRUE");
            userRepository.save(user); // Attempt to add to DB
            JsonObject responseObject = new JsonObject();
            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "User registered successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(responseObject)); // If success

        } catch (DataIntegrityViolationException ex) {
            // If problem with data integrity,
            // we assume that it's related to the email already existing, since the validation logic
            // makes sure that everything is in order
            JsonObject errorObject = new JsonObject();
            errorObject.addProperty("success", false);
            errorObject.addProperty("message", "Email is already in use.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorObject));
        }
    }
}
