package dev.develya.cova.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.develya.cova.model.Car;
import dev.develya.cova.dto.CarRequest;
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

    private Gson gson = new Gson();

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public List<Car> all() {
        return carRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCar(@Valid @RequestBody CarRequest carRequest) {
        JsonObject responseObject = new JsonObject();

        try {
            // Check if the driver exists
            Optional<User> optionalUser = userRepository.findById(carRequest.getDriverID());
            if (optionalUser.isEmpty()) { // If doesn't exist
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "Driver not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(responseObject));
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

            carRepository.save(car); // Attempt to add to DB

            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "Car added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(responseObject));

        } catch (Exception exception) { // Unknown error
            responseObject.addProperty("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(responseObject));
        }
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<String> getCars(@PathVariable("driverId") Integer driverId) {
        JsonObject responseObject = new JsonObject();

        try {
            if (userRepository.existsByUserID(driverId)) { // If driver exists
                List<Car> cars = carRepository.findByDriver_UserID(driverId);

                JsonArray carsArray = new JsonArray();
                cars.forEach(car -> {
                    JsonObject carObject = new JsonObject();
                    carObject.addProperty("carID", car.getCarID());
                    carObject.addProperty("driverID", car.getDriver().getUserID());
                    carObject.addProperty("brand", car.getBrand());
                    carObject.addProperty("model", car.getModel());
                    carObject.addProperty("carYear", car.getCarYear());
                    carObject.addProperty("color", car.getColor());
                    carObject.addProperty("licensePlate", car.getLicensePlate());
                    carObject.addProperty("serialNumber", car.getSerialNumber());
                    carObject.addProperty("numberOfSeats", car.getNumberOfSeats());

                    carsArray.add(carObject);
                });

                responseObject.addProperty("success", true);
                responseObject.add("cars", carsArray);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject.toString());
            } else { // If it doesn't exist
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "Driver not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject.toString());
            }
        } catch (Exception exception) { // Unknown error
            responseObject.addProperty("success", false);
            responseObject.addProperty("message", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject.toString());
        }
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<String> updateCar(@PathVariable("carId") Integer carId,
                                            @Valid @RequestBody CarRequest updatedCarRequest) {
        JsonObject responseObject = new JsonObject();

        try {
            Optional<Car> optionalCar = carRepository.findById(carId);
            if (optionalCar.isEmpty()) {
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "Car not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(responseObject));
            }

            Car existingCar = optionalCar.get();
            // Update car fields with the new values
            existingCar.setBrand(updatedCarRequest.getBrand());
            existingCar.setModel(updatedCarRequest.getModel());
            existingCar.setCarYear(updatedCarRequest.getCarYear());
            existingCar.setColor(updatedCarRequest.getColor());
            existingCar.setLicensePlate(updatedCarRequest.getLicensePlate());
            existingCar.setSerialNumber(updatedCarRequest.getSerialNumber());
            existingCar.setNumberOfSeats(updatedCarRequest.getNumberOfSeats());

            // Save updated car to database
            carRepository.save(existingCar);

            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "Car updated successfully.");
            return ResponseEntity.ok(gson.toJson(responseObject));
        } catch (Exception exception) { // Unknown error
            responseObject.addProperty("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(responseObject));
        }
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") Integer carId) {
        JsonObject responseObject = new JsonObject();

        try {
            Optional<Car> optionalCar = carRepository.findById(carId);
            if (optionalCar.isEmpty()) {
                responseObject.addProperty("success", false);
                responseObject.addProperty("message", "Car not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(responseObject));
            }

            // Delete car from database
            carRepository.deleteById(carId);

            responseObject.addProperty("success", true);
            responseObject.addProperty("message", "Car deleted successfully.");
            return ResponseEntity.ok(gson.toJson(responseObject));

        } catch (Exception exception) { // Unknown error
            responseObject.addProperty("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(responseObject));
        }
    }
}
