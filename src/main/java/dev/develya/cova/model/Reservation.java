package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationID")
    private Integer reservationID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PassengerID", referencedColumnName = "userID")
    private User passenger;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "JourneyID", referencedColumnName = "journeyID")
    private Journey journey;

    @NotNull
    @Column(name = "ReservationDate")
    private LocalDateTime reservationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ReservationStatus")
    private ReservationStatus reservationStatus;

    public enum ReservationStatus {
        Confirmed,
        Pending,
        Cancelled
    }
}
