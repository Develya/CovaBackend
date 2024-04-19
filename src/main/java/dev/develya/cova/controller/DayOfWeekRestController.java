package dev.develya.cova.controller;

import dev.develya.cova.model.DayOfWeek;
import dev.develya.cova.repository.DayOfWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/daysofweek")
public class DayOfWeekRestController {

    @Autowired
    DayOfWeekRepository dayOfWeekRepository;

    @GetMapping("/all")
    public List<DayOfWeek> all() {
        return dayOfWeekRepository.findAll();
    }
}
