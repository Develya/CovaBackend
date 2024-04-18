package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Journeys")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JourneyID")
    private Integer journeyID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "DriverID", referencedColumnName = "userID")
    private User driver;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TrajetID", referencedColumnName = "trajetID")
    private Trajet trajet;

    @NotNull
    @Column(name = "AvailableSeats")
    private Integer availableSeats;

    @NotNull
    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "JourneyCreationDate")
    private LocalDateTime journeyCreationDate;

    @Column(name = "IsActive")
    private Boolean isActive;
}

