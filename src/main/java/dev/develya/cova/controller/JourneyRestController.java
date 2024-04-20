package dev.develya.cova.controller;

import dev.develya.cova.model.Journey;
import dev.develya.cova.model.Trajet;
import dev.develya.cova.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journeys")
public class JourneyRestController {

    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    ReservationRestController reservationRestController;

    @GetMapping("/all")
    public List<Journey> all() {
        return journeyRepository.findAll();
    }

    public void findJourneywithTrajet(Optional<Trajet> trajet){
        List<Journey> journeys = journeyRepository.findAllByTrajet(trajet);
        if (journeys != null){
            //Damn we need to go see the reservation Hope its the last stop
            journeys.forEach(journey -> reservationRestController.findAllByReservation(journey));
            journeyRepository.deleteAll(journeys);
        }
    }
}
