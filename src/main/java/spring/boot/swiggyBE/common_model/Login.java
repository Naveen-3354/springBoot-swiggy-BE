package spring.boot.swiggyBE.common_model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @Email(message = "Please enter a valid email address.")
    @NotNull(message = "Eamil field should not be empty.")
    private String email;

    @NotNull(message = "Password feild should not be empty.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contain at least one uppercase letter, " +
                    "one lowercase letter, one digit, " +
                    "and be at least 8 characters long")
    private String password;
}
