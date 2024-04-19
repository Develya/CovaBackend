package dev.develya.cova.controller;

import dev.develya.cova.model.User;
import dev.develya.cova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
