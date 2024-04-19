package dev.develya.cova.model;

import dev.develya.cova.validation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userID;

    @NotBlank
    @Size(max = 255)
    @Column(name = "lastname")
    private String lastName;

    @NotBlank
    @Size(max = 255)
    @Column(name = "firstname")
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "\\d{10,20}", message = "Telephone number must be between 10 and 20 digits")
    @Column(name = "telephone")
    private String telephone;

    @Past
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @ValidPassword
    @Column(name = "hashedpassword")
    private String hashedPassword;

    @NotNull
    @Past
    @Column(name = "registrationdate")
    private LocalDateTime registrationDate;

    @NotBlank
    @Size(max = 50)
    @Column(name = "preferredlanguage")
    private String preferredLanguage;

    @Size(max = 100)
    @Column(name = "profession")
    private String profession;

    @Column(name = "hobbiesinterests")
    private String hobbiesInterests;

    @Size(max = 255)
    @Column(name = "emergencycontact")
    private String emergencyContact;

    @Size(max = 255)
    @Column(name = "profilephoto")
    private String profilePhoto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "notificationpreferences")
    private NotificationPreference notificationPreferences;

    @NotBlank
    @Column(name = "isactive")
    private String isActive;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public enum Gender {
        Male,
        Female,
        Other
    }

    public enum NotificationPreference {
        Email,
        SMS,
        Push_Notification
    }

    public enum Role {
        Driver,
        Passenger
    }
}