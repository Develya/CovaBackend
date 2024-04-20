package dev.develya.cova.controller;

import dev.develya.cova.model.Trajet;
import dev.develya.cova.repository.TrajetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TrajetRepository trajetRepository;

    @Autowired
    JourneyRestController journeyRestController;

    @GetMapping("/all")
    public List<Trajet> all() {
        return trajetRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Trajet trajet, BindingResult bindingResult){
        ResponseEntity<?> errors = getResponseEntity(bindingResult);
        if (errors != null) return errors;

        trajetRepository.save(trajet);

        return ResponseEntity.status(HttpStatus.CREATED).body("Trajet registered successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrajet(@PathVariable int id) {

        if (trajetRepository.existsById(id)) {
            Optional<Trajet> trajet = trajetRepository.findById(id);
            //damn lets go see the journeysssss
            journeyRestController.findJourneywithTrajet(trajet);
            trajetRepository.deleteById(id);
            return ResponseEntity.ok("Trajet deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trajet not found.");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> modifyTrajet(@PathVariable int id, @Valid @RequestBody Trajet trajet){

        Optional<Trajet> existingTrajet = trajetRepository.findById(id);
        if(existingTrajet.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trajet not found.");
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
        return  ResponseEntity.ok("Trajet update successfully.");
    }

    static ResponseEntity<?> getResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            // Return field validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return null;
    }

}
