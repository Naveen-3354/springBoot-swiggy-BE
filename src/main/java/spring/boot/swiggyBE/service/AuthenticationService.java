package spring.boot.swiggyBE.service;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.common_model.Login;
import spring.boot.swiggyBE.common_model.Register;
import spring.boot.swiggyBE.common_model.Status;
import spring.boot.swiggyBE.database_model.Roles;
import spring.boot.swiggyBE.database_model.Users;
import spring.boot.swiggyBE.repository.UserRepository;
import spring.boot.swiggyBE.security.components.JwtUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity addNewUser(Register register) {
        try {
            boolean emailExist = userRepository.existsByEmail(register.getEmail());
            boolean numberExist = userRepository.existsByMobileNumber(register.getNumber());
            if (!emailExist) {

                if (!numberExist) {
                    Users newUsers = Users.builder()
                            .userName(register.getUserName())
                            .email(register.getEmail())
                            .mobileNumber(register.getNumber())
                            .password(passwordEncoder.encode(register.getPassword()))
                            .status(Status.ACTIVE)
                            .createdOn(new Date())
                            .roles(new HashSet<>(List.of(new Roles[]{Roles.ROLE_USER})))
                            .build();
                    userRepository.save(newUsers);
                    return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Mobile number already registered.");
                }
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("!Server stopped working.");
        }
    }

    public ResponseEntity<?> verifyUser(Login login) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(jwt);
    }
}
