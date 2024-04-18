package dev.develya.cova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "DaysOfWeek")
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DayID")
    private Integer dayID;

    @NotBlank
    @Size(max = 20)
    @Column(name = "DayName")
    private String dayName;
}
