package dev.develya.cova.controller;

import dev.develya.cova.model.Rating;
import dev.develya.cova.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingRestController {

    @Autowired
    RatingRepository ratingRepository;

    @GetMapping("/all")
    public List<Rating> all() {
        return ratingRepository.findAll();
    }
}
