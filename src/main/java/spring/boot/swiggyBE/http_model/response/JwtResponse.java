package spring.boot.swiggyBE.http_model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String message;
    private String D;
    private String email;
    private List<String> roles;
}
