package spring.boot.swiggyBE.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.boot.swiggyBE.common_model.Login;
import spring.boot.swiggyBE.common_model.Register;
import spring.boot.swiggyBE.service.AuthenticationService;

@RestController
@Validated
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService userService;

    @GetMapping("/test")
    public String test(){
        return "Test api works";
    }

    @PostMapping("/register")
    public ResponseEntity addNewUser(@Valid @RequestBody Register register){
        return userService.addNewUser(register);
    }
    @PostMapping("/login")
    public ResponseEntity verifyUSer(@Valid @RequestBody Login login){
        return userService.verifyUser(login);
    }
}
