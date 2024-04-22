package dev.develya.cova.dto;

import dev.develya.cova.validation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email not valid")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Column(name = "hashedpassword")
    private String hashedPassword;
}
