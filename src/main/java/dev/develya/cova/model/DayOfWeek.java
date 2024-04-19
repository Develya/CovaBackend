package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "daysofweek")
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayid")
    private Integer dayID;

    @NotBlank
    @Size(max = 20)
    @Column(name = "dayname")
    private String dayName;
}
