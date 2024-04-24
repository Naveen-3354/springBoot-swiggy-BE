package spring.boot.swiggyBE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.swiggyBE.common_model.Status;
import spring.boot.swiggyBE.service.UserService;

import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @GetMapping("/getByNumber/{number}")
    public ResponseEntity getByMobileNumber(@PathVariable String number) {
        return userService.getByMobileNumber(number);
    }

    @GetMapping("/getByStatus/{status}")
    public ResponseEntity getByStatus(@PathVariable Status status) {
        return userService.getByStatus(status);
    }

    @PutMapping("/updateEmail/{id}")
    public ResponseEntity updateEmail(@PathVariable String id, Map<String, String> values) {
        return userService.updateEmail(id, values.get("email"));
    }

    @PutMapping("/updateNumber/{id}")
    public ResponseEntity updateNumber(@PathVariable String id, Map<String, String> values) {
        return userService.updateMobileNmber(id, values.get("Number"));
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity updatePassword(@PathVariable String id, Map<String, String> values) {
        return userService.updatePassword(id, values.get("Password"));
    }

    @PutMapping("/updateRoles/{id}")
    public ResponseEntity updateRoles(@PathVariable String id, Map<String, ? extends Set> values) {
        return userService.updateRoles(id, values.get("roles"));
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity updateStatus(@PathVariable String id, Map<String,String > values) {
        return userService.updateStatusInactive(id, values.get("status"));
    }
}
