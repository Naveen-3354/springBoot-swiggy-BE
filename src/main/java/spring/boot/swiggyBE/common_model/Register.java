package spring.boot.swiggyBE.common_model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    @NotNull(message = "Username field should not be empty.")
    @Size(min = 3, max = 15, message = "Username length must be between 3 and 15 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,15}$", message = "Username can only contain letters, digits, and underscores.")
    private String userName;

    @Email(message = "Please enter a valid email address.")
    @NotNull(message = "Eamil field should not be empty.")
    private String email;

    @NotNull(message = "Phone number field should not be empty.")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Please enter a valid phone number.")
    private String number;

    @NotNull(message = "Password feild should not be empty.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and be at least 8 characters long")
    private String password;
}
