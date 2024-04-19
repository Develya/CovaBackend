package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "journeys")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journeyid")
    private Integer journeyID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "driverid", referencedColumnName = "userid")
    private User driver;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "trajetid", referencedColumnName = "trajetid")
    private Trajet trajet;

    @NotNull
    @Column(name = "availableseats")
    private Integer availableSeats;

    @NotNull
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "journeycreationdate")
    private LocalDateTime journeyCreationDate;

    @Column(name = "isactive")
    private Boolean isActive;
}

