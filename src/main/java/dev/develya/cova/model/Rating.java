package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingID")
    private Integer ratingID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RaterID", referencedColumnName = "userID")
    private User rater;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RatedID", referencedColumnName = "userID")
    private User rated;

    @NotNull
    @Column(name = "Score")
    private Integer score;

    @NotNull
    @Column(name = "RatingDate")
    private LocalDateTime ratingDate;

    @Lob
    @Column(name = "Comment", columnDefinition = "TEXT")
    private String comment;
}

