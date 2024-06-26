package dev.develya.cova.model;

import com.google.gson.JsonObject;
import dev.develya.cova.validation.NullOrNotBlank;
import dev.develya.cova.validation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

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

    @NotBlank(message = "Last Name must not be blank")
    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = "First name must not be blank")
    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "firstname")
    private String firstName;

    // Custom telephone validator?
    @NotBlank(message = "Telephone must not be blank")
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

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email not valid")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @ValidPassword
    @Column(name = "hashedpassword")
    private String hashedPassword;

    @Column(name = "registrationdate")
    private LocalDateTime registrationDate = LocalDateTime.now();

    @NotBlank(message = "Preferred language must not be blank")
    @Size(max = 50, message = "Cannot exceed 50 characters")
    @Column(name = "preferredlanguage")
    private String preferredLanguage;

    @NullOrNotBlank(message = "Profession must not be blank")
    @Size(max = 100, message = "Cannot exceed 100 characters")
    @Column(name = "profession")
    private String profession;

    @NullOrNotBlank(message = "Hobbies/interests must not be blank")
    @Column(name = "hobbiesinterests")
    private String hobbiesInterests;

    @NullOrNotBlank(message = "Emergency contact must not be blank")
    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "emergencycontact")
    private String emergencyContact;

    @NullOrNotBlank(message = "Profile photo URL must not be blank")
    @URL
    @Size(max = 255, message = "Cannot exceed 255 characters")
    @Column(name = "profilephoto")
    private String profilePhoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "notificationpreferences")
    private NotificationPreference notificationPreferences;

    @NullOrNotBlank(message = "Active status must not be blank")
    @Column(name = "isactive")
    private String isActive = "TRUE";

    @NotNull(message = "Role must not be blank")
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

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userID", userID);
        jsonObject.addProperty("lastName", lastName);
        jsonObject.addProperty("firstName", firstName);
        jsonObject.addProperty("telephone", telephone);
        jsonObject.addProperty("dateOfBirth", dateOfBirth != null ? dateOfBirth.toString() : "");
        jsonObject.addProperty("gender", gender != null ? gender.toString() : "");
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("hashedPassword", hashedPassword);
        jsonObject.addProperty("registrationDate", registrationDate.toString());
        jsonObject.addProperty("preferredLanguage", preferredLanguage);
        jsonObject.addProperty("profession", profession != null ? profession : "");
        jsonObject.addProperty("hobbiesInterests", hobbiesInterests != null ? hobbiesInterests : "");
        jsonObject.addProperty("emergencyContact", emergencyContact != null ? emergencyContact : "");
        jsonObject.addProperty("profilePhoto", profilePhoto != null ? profilePhoto : "");
        jsonObject.addProperty("notificationPreferences", notificationPreferences != null ? notificationPreferences.toString() : "");
        jsonObject.addProperty("isActive", isActive);
        jsonObject.addProperty("role", role != null ? role.toString() : "");
        return jsonObject;
    }
}