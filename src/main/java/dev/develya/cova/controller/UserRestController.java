package dev.develya.cova.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.develya.cova.dto.UserRequest;
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

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping("/all")
    public List<User> all() {
        return userRepository.findAll();
    }


    /* Future improvement for login and register:

        - hashing for passwords

       Future improvement for every endpoint:
        - Authorization with roles
        - JSON web token (https://www.baeldung.com/spring-security-sign-jwt-token)
        -
    */


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        JsonObject responseObject = new JsonObject();
        try {
            if (userRepository.existsByEmail(user.getEmail())) { // If email is already used
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "Email is already in use.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(responseObject));

            }
            userRepository.save(user); // Attempt to add to DB
            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "User registered successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(responseObject)); // If success

        } catch (Exception exception) { // Unknown error
            responseObject.addProperty("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(exception.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserRequest userRequest) {
        JsonObject responseObject = new JsonObject();
        try {

            /* Future improvement:
                - prevent code injection
                - hashing for passwords
            */

            // Attempt to find user with matching credentials in the DB
            User user = userRepository.findByEmailAndAndHashedPassword(userRequest.getEmail(), userRequest.getHashedPassword());

            if (user != null) { // if credentials matched
                responseObject.addProperty("success", true);
                responseObject.addProperty("message", "Logged in successfully.");
                responseObject.add("user", user.toJson());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(gson.toJson(responseObject));

            } else { // if credentials are not valid
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "Invalid credentials");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(gson.toJson(responseObject));
            }

        } catch (Exception exception) { // Unknown error
            responseObject.addProperty("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(responseObject));
        }
    }
}
