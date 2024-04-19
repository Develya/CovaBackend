package dev.develya.cova.controller;

import dev.develya.cova.model.Journey;
import dev.develya.cova.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/journeys")
public class JourneyRestController {

    @Autowired
    JourneyRepository journeyRepository;

    @GetMapping("/all")
    public List<Journey> all() {
        return journeyRepository.findAll();
    }
}
