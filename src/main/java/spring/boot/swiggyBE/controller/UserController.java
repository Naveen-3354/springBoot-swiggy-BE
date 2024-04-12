package spring.boot.swiggyBE.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.swiggyBE.common_model.Register;
import spring.boot.swiggyBE.service.UserService;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "Test api works";
    }

    @PostMapping("/register")
    public ResponseEntity addNewUser(@Valid @RequestBody Register register){
        
        return userService.addNewUser(register);
    }
}
