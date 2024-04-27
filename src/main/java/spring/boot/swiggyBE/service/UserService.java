package spring.boot.swiggyBE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.http_model.request.Status;
import spring.boot.swiggyBE.database_model.Users;
import spring.boot.swiggyBE.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    public ResponseEntity<?> getByEmail(String email){
        Optional<Users> users = userRepository.findByEmail(email);
        return users.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> (ResponseEntity) ResponseEntity.notFound());
    }

    public ResponseEntity<?> getByMobileNumber(String number){
        Optional<Users> users = userRepository.findByMobileNumber(number);
        return users.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> (ResponseEntity) ResponseEntity.notFound());
    }

    public ResponseEntity<?> getByStatus(Status status){
        Optional<Users> users = userRepository.findByStatus(status);
        if(users.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(users.get());
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }


    public ResponseEntity<?> updateEmail(String id, String email){
        Optional<Users> users = userRepository.findById(id);
        if(users.isPresent()){
            users.get().setEmail(email);
            userRepository.save(users.get());
            return ResponseEntity.ok("User email updated.");
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    public ResponseEntity<?> updateMobileNmber(String id, String number){
        Optional<Users> users = userRepository.findById(id);
        if(users.isPresent()){
            users.get().setMobileNumber(number);
            userRepository.save(users.get());
            return ResponseEntity.ok("User number updated.");
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    public ResponseEntity<?> updatePassword(String id, String password){
        Optional<Users> users = userRepository.findById(id);
        if(users.isPresent()){
            users.get().setPassword(passwordEncoder.encode(password));
            userRepository.save(users.get());
            return ResponseEntity.ok("User password updated.");
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    public ResponseEntity<?> updateRoles(String id, Set roles){
        Optional<Users> users = userRepository.findById(id);
        if(users.isPresent()){
            users.get().setRoles(roles);
            userRepository.save(users.get());
            return ResponseEntity.ok("User roles updated.");
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    public ResponseEntity<?> updateStatusInactive(String id, String  status){
        Optional<Users> users = userRepository.findById(id);
        if(users.isPresent()){
            users.get().setStatus(Status.valueOf(status));
            userRepository.save(users.get());
            return ResponseEntity.ok("User status updated.");
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }
}
