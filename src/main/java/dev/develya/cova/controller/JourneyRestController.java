package dev.develya.cova.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.develya.cova.model.Journey;
import dev.develya.cova.model.Trajet;
import dev.develya.cova.repository.JourneyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journeys")
public class JourneyRestController {
    private Gson gson = new Gson();
    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    ReservationRestController reservationRestController;

    @GetMapping("/all")
    public List<Journey> all() {
        return journeyRepository.findAll();
    }

    @PostMapping("/addJourney")
    public ResponseEntity<?> createJourney(@Valid @RequestBody Journey journey){
        try{
            journeyRepository.save(journey);
            JsonObject responseObject = new JsonObject();
            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "journey create successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(responseObject)); // If success
        }catch(DataIntegrityViolationException ex){
            // If problem with data integrity,
            // we assume that it's related to the email already existing, since the validation logic
            // makes sure that everything is in order
            JsonObject errorObject = new JsonObject();
            errorObject.addProperty("success", false);
            errorObject.addProperty("message", "cheh");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorObject));
        }
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
