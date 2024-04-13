package spring.boot.swiggyBE.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.common_model.Login;
import spring.boot.swiggyBE.common_model.Register;
import spring.boot.swiggyBE.common_model.response.JwtResponse;
import spring.boot.swiggyBE.common_model.Status;
import spring.boot.swiggyBE.database_model.Roles;
import spring.boot.swiggyBE.database_model.Users;
import spring.boot.swiggyBE.repository.UserRepository;
import spring.boot.swiggyBE.security.components.JwtUtils;
import spring.boot.swiggyBE.security.components.UserDetailsImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;



    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

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
                            .password(register.getPassword())
                            .status(Status.ACTIVE)
                            .createdOn(new Date())
                            .roles(new HashSet<>(List.of(new Roles[]{Roles.ROLE_USER})))
                            .build();
                    return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Mobile number already registered.");
                }
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("!Server stopped working.");
        }
    }

    public ResponseEntity<?> verifyUser(Login login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}
