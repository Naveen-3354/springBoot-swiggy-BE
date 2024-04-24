package spring.boot.swiggyBE.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.swiggyBE.common_model.Status;
import spring.boot.swiggyBE.database_model.Users;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    boolean existsByMobileNumber(String number);

    boolean existsByEmail(String email);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByMobileNumber(String number);
    Optional<Users> findByStatus(Status status);
}
