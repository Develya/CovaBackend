package dev.develya.cova.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.develya.cova.model.Trajet;
import dev.develya.cova.repository.TrajetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/trajets")
public class TrajetRestController {

    private Gson gson = new Gson();

    @Autowired
    TrajetRepository trajetRepository;

    @Autowired
    JourneyRestController journeyRestController;

    @GetMapping("/all")
    public List<Trajet> all() {
        return trajetRepository.findAll();
    }
    @GetMapping("/findByTrajetId/{id}")
    public Trajet byTrajetID(@PathVariable int id){
        Optional<Trajet> trajet = trajetRepository.findById(id);
        Trajet trajet1 = trajet.get();
        return trajet1;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createTrajetTabarnak(@Valid @RequestBody Trajet trajet){
        try {
            trajetRepository.save(trajet);
            JsonObject responseObject = new JsonObject();
            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "trajet create successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(responseObject)); // If success
        }catch (DataIntegrityViolationException ex){
            // If problem with data integrity,
            // we assume that it's related to the email already existing, since the validation logic
            // makes sure that everything is in order
            JsonObject errorObject = new JsonObject();
            errorObject.addProperty("success", false);
            errorObject.addProperty("message", "Email is already in use.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorObject));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrajet(@PathVariable int id) {
        JsonObject responseObject = new JsonObject();
        try {
            if (trajetRepository.existsById(id)) {
                Optional<Trajet> trajet = trajetRepository.findById(id);
                //damn lets go see the journeysssss
                journeyRestController.findJourneywithTrajet(trajet);
                trajetRepository.deleteById(id);
                responseObject.addProperty("success", true);
                responseObject.addProperty("message", "trajet delete successfully.");
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(responseObject));
            } else {
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "Trajet not found");
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(responseObject));
            }
        }catch (Exception ex){
            responseObject.addProperty("success", false);
            responseObject.addProperty("message", "trajet couldn't delete.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(responseObject));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> modifyTrajet(@PathVariable int id, @Valid @RequestBody Trajet trajet){
        JsonObject responseObject = new JsonObject();
        try {
            Optional<Trajet> existingTrajet = trajetRepository.findById(id);
            if (existingTrajet.isEmpty()) {
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "trajet not found.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(responseObject));
            }

            //The trajet is not Optionnal
            Trajet trajet1 = existingTrajet.get();
            //Change the information for the trajet
            trajet1.setDepartureAddress(trajet.getDepartureAddress());
            trajet1.setDesiredArrivalTime(trajet.getDesiredArrivalTime());
            trajet1.setDesiredDepartureTime(trajet.getDesiredDepartureTime());
            trajet1.setDestinationAddress(trajet.getDestinationAddress());
            trajet1.setDayOfWeek(trajet.getDayOfWeek());
            //Update the data
            trajetRepository.save(trajet1);
            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "trajet update successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(responseObject));
        }catch (Exception ex){
            responseObject.addProperty("success", false);
            responseObject.addProperty("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(responseObject));
        }
    }

}
