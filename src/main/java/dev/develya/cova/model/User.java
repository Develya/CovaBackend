package dev.develya.cova.model;

import dev.develya.cova.validation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Integer userID;

    @NotBlank
    @Size(max = 255)
    @Column(name = "LastName")
    private String lastName;

    @NotBlank
    @Size(max = 255)
    @Column(name = "FirstName")
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "\\d{10,20}", message = "Telephone number must be between 10 and 20 digits")
    @Column(name = "Telephone")
    private String telephone;

    @Past
    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;

    @NotBlank
    @Email
    @Column(name = "Email", unique = true)
    private String email;

    @NotBlank
    @ValidPassword
    @Column(name = "HashedPassword")
    private String hashedPassword;

    @NotNull
    @Past
    @Column(name = "RegistrationDate")
    private LocalDateTime registrationDate;

    @NotBlank
    @Size(max = 50)
    @Column(name = "PreferredLanguage")
    private String preferredLanguage;

    @Size(max = 100)
    @Column(name = "Profession")
    private String profession;

    @Column(name = "HobbiesInterests")
    private String hobbiesInterests;

    @Size(max = 255)
    @Column(name = "EmergencyContact")
    private String emergencyContact;

    @Size(max = 255)
    @Column(name = "ProfilePhoto")
    private String profilePhoto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "NotificationPreferences")
    private NotificationPreferences notificationPreferences;

    @NotBlank
    @Column(name = "IsActive")
    private String isActive;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

    public enum Gender {
        Male,
        Female,
        Other
    }

    public enum NotificationPreferences {
        Email,
        SMS,
        Push_Notification
    }

    public enum Role {
        Driver,
        Passenger
    }
}