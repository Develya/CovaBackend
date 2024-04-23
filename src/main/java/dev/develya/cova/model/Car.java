package dev.develya.cova.model;

import com.google.gson.JsonObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carid")
    private Integer carID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "driverid", referencedColumnName = "userid")
    private User driver;

    @NotBlank
    @Size(max = 100)
    @Column(name = "brand")
    private String brand;

    @NotBlank
    @Size(max = 100)
    @Column(name = "model")
    private String model;

    @NotNull
    @Positive
    @Column(name = "caryear")
    private Integer carYear;

    @NotBlank
    @Size(max = 50)
    @Column(name = "color")
    private String color;

    @NotBlank
    @Size(max = 20)
    @Column(name = "licenseplate")
    private String licensePlate;

    @NotBlank
    @Size(max = 50)
    @Column(name = "serialnumber")
    private String serialNumber;

    @NotNull
    @Positive
    @Column(name = "numberofseats")
    private Integer numberOfSeats;

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("carID", carID);
        jsonObject.addProperty("driverID", driver != null ? driver.getUserID() : null);
        jsonObject.addProperty("brand", brand);
        jsonObject.addProperty("model", model);
        jsonObject.addProperty("carYear", carYear);
        jsonObject.addProperty("color", color);
        jsonObject.addProperty("licensePlate", licensePlate);
        jsonObject.addProperty("serialNumber", serialNumber);
        jsonObject.addProperty("numberOfSeats", numberOfSeats);
        return jsonObject;
    }
}
