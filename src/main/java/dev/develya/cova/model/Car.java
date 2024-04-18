package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "Cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private Integer carID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "DriverID", referencedColumnName = "userID")
    private User driver;

    @NotBlank
    @Size(max = 100)
    @Column(name = "Brand")
    private String brand;

    @NotBlank
    @Size(max = 100)
    @Column(name = "Model")
    private String model;

    @NotNull
    @Positive
    @Column(name = "CarYear")
    private Integer carYear;

    @NotBlank
    @Size(max = 50)
    @Column(name = "Color")
    private String color;

    @NotBlank
    @Size(max = 20)
    @Column(name = "LicensePlate")
    private String licensePlate;

    @NotBlank
    @Size(max = 50)
    @Column(name = "SerialNumber")
    private String serialNumber;

    @NotNull
    @Positive
    @Column(name = "NumberOfSeats")
    private Integer numberOfSeats;
}
