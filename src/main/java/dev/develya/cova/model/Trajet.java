package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "trajets")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trajetid")
    private Integer trajetID;

    @NotBlank
    @Size(max = 255)
    @Column(name = "departureaddress")
    private String departureAddress;

    @NotBlank
    @Size(max = 255)
    @Column(name = "destinationaddress")
    private String destinationAddress;

    @NotNull
    @Column(name = "desireddeparturetime")
    private LocalDateTime desiredDepartureTime;

    @Column(name = "desiredarrivaltime")
    private LocalDateTime desiredArrivalTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dayid", referencedColumnName = "dayid")
    private DayOfWeek dayOfWeek;
}

