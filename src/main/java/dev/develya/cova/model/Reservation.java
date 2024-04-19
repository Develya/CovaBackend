package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationid")
    private Integer reservationID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "passengerid", referencedColumnName = "userid")
    private User passenger;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "journeyid", referencedColumnName = "journeyid")
    private Journey journey;

    @NotNull
    @Column(name = "reservationdate")
    private LocalDateTime reservationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reservationstatus")
    private ReservationStatus reservationStatus;

    public enum ReservationStatus {
        Confirmed,
        Pending,
        Cancelled
    }
}
