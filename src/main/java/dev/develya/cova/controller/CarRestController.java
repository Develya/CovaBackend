package dev.develya.cova.controller;

import dev.develya.cova.model.Car;
import dev.develya.cova.model.CarRequest;
import dev.develya.cova.model.User;
import dev.develya.cova.repository.CarRepository;
import dev.develya.cova.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarRestController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<Car> all() {
        return carRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCar(@Valid @RequestBody CarRequest carRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Check if the driver exists
        Optional<User> optionalUser = userRepository.findById(carRequest.getDriverId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found.");
        }

        // Create a new Car object from the CarRequest
        Car car = new Car();
        car.setDriver(optionalUser.get());
        car.setBrand(carRequest.getBrand());
        car.setModel(carRequest.getModel());
        car.setCarYear(carRequest.getCarYear());
        car.setColor(carRequest.getColor());
        car.setLicensePlate(carRequest.getLicensePlate());
        car.setSerialNumber(carRequest.getSerialNumber());
        car.setNumberOfSeats(carRequest.getNumberOfSeats());

        // Save car to database
        carRepository.save(car);

        return ResponseEntity.status(HttpStatus.CREATED).body("Car added successfully.");
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<?> getCarsByDriver(@PathVariable("driverId") Integer driverId) {
        // Retrieve cars by driver ID
        List<Car> cars = carRepository.findByDriver_UserID(driverId);

        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cars found for the driver.");
        }

        return ResponseEntity.ok(cars);
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<?> updateCar(@PathVariable("carId") Integer carId,
                                       @Valid @RequestBody Car updatedCar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }

        Car existingCar = optionalCar.get();
        // Update car fields with the new values
        existingCar.setBrand(updatedCar.getBrand());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setCarYear(updatedCar.getCarYear());
        existingCar.setColor(updatedCar.getColor());
        existingCar.setLicensePlate(updatedCar.getLicensePlate());
        existingCar.setSerialNumber(updatedCar.getSerialNumber());
        existingCar.setNumberOfSeats(updatedCar.getNumberOfSeats());

        // Save updated car to database
        carRepository.save(existingCar);

        return ResponseEntity.ok("Car updated successfully.");
    }


    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable("carId") Integer carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }

        // Delete car from database
        carRepository.deleteById(carId);

        return ResponseEntity.ok("Car deleted successfully.");
    }
}
