package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingid")
    private Integer ratingID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "raterid", referencedColumnName = "userid")
    private User rater;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ratedid", referencedColumnName = "userid")
    private User rated;

    @NotNull
    @Column(name = "score")
    private Integer score;

    @NotNull
    @Column(name = "ratingdate")
    private LocalDateTime ratingDate;

    @Lob
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
}

