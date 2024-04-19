package dev.develya.cova.controller;

import dev.develya.cova.model.Car;
import dev.develya.cova.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarRestController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/all")
    public List<Car> all() {
        return carRepository.findAll();
    }
}
