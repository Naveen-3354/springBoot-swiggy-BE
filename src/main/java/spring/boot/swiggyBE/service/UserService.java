package spring.boot.swiggyBE.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.common_model.Register;
import spring.boot.swiggyBE.common_model.Response;
import spring.boot.swiggyBE.common_model.Status;
import spring.boot.swiggyBE.database_model.Users;
import spring.boot.swiggyBE.repository.UserRepository;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
