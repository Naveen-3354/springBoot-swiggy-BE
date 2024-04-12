package spring.boot.swiggyBE.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.swiggyBE.database_model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    boolean existsByMobileNumber(String number);

    boolean existsByEmail(String email);
}
