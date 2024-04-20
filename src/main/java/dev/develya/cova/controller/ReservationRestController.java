package dev.develya.cova.controller;

import dev.develya.cova.model.Journey;
import dev.develya.cova.model.Reservation;
import dev.develya.cova.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;


    @GetMapping("/all")
    public List<Reservation> all() {
        return reservationRepository.findAll();
    }

    public void findAllByReservation(Journey journey){
        List<Reservation> reservations = reservationRepository.findAllByJourney(journey);
        if (reservations != null){
            //Yes I can just delete now
            reservationRepository.deleteAll(reservations);

        }
    }
}
