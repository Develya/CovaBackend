package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "Trajets")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrajetID")
    private Integer trajetID;

    @NotBlank
    @Size(max = 255)
    @Column(name = "DepartureAddress")
    private String departureAddress;

    @NotBlank
    @Size(max = 255)
    @Column(name = "DestinationAddress")
    private String destinationAddress;

    @NotNull
    @Column(name = "DesiredDepartureTime")
    private LocalDateTime desiredDepartureTime;

    @Column(name = "DesiredArrivalTime")
    private LocalDateTime desiredArrivalTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "userID")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "DayID", referencedColumnName = "dayID")
    private DayOfWeek dayOfWeek;
}

