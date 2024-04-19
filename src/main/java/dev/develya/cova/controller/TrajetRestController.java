package dev.develya.cova.controller;

import dev.develya.cova.model.Trajet;
import dev.develya.cova.repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trajets")
public class TrajetRestController {

    @Autowired
    TrajetRepository trajetRepository;

    @GetMapping("/all")
    public List<Trajet> all() {
        return trajetRepository.findAll();
    }
}
