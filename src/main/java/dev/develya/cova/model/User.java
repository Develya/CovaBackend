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

    @NotBlank(message = "Cannot be blank")
    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = "Cannot be blank")
    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "firstname")
    private String firstName;

    @NotBlank(message = "Cannot be blank")
    @Size(max = 20, message = "Cannot exceed 20 characters")
    @Pattern(regexp = "\\d{10,20}", message = "Telephone number must be between 10 and 20 digits")
    @Column(name = "telephone")
    private String telephone;

    @Past(message = "Date must be in the past")
    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotBlank(message = "Cannot be blank")
    @Email(message = "Email not valid")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Cannot be blank")
    @ValidPassword
    @Column(name = "hashedpassword")
    private String hashedPassword;

    @NotNull(message = "Cannot be blank")
    @Past
    @Column(name = "registrationdate")
    private LocalDateTime registrationDate;

    @NotBlank(message = "Cannot be blank")
    @Size(max = 50, message = "Cannot exceed 50 characters")
    @Column(name = "preferredlanguage")
    private String preferredLanguage;

    @Size(max = 100, message = "Cannot exceed 100 characters")
    @Column(name = "profession")
    private String profession;

    @Column(name = "hobbiesinterests")
    private String hobbiesInterests;

    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "emergencycontact")
    private String emergencyContact;

    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "profilephoto")
    private String profilePhoto;

    @NotNull(message = "Cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(name = "notificationpreferences")
    private NotificationPreference notificationPreferences;

    @NotBlank(message = "Cannot be blank")
    @Column(name = "isactive")
    private String isActive;

    @NotNull(message = "Cannot be blank")
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