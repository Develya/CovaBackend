package dev.develya.cova.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarRequest {

    @NotNull(message = "Driver ID cannot be null")
    private Integer driverId;

    @NotBlank(message = "Brand is required")
    @Size(max = 100, message = "Brand must be at most 100 characters")
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(max = 100, message = "Model must be at most 100 characters")
    private String model;

    @NotNull(message = "Car year is required")
    @Positive(message = "Car year must be a positive number")
    private Integer carYear;

    @NotBlank(message = "Color is required")
    @Size(max = 50, message = "Color must be at most 50 characters")
    private String color;

    @NotBlank(message = "License plate is required")
    @Size(max = 20, message = "License plate must be at most 20 characters")
    private String licensePlate;

    @NotBlank(message = "Serial number is required")
    @Size(max = 50, message = "Serial number must be at most 50 characters")
    private String serialNumber;

    @NotNull(message = "Number of seats is required")
    @Positive(message = "Number of seats must be a positive number")
    private Integer numberOfSeats;
}
