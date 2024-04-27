package spring.boot.swiggyBE.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.swiggyBE.database_model.Manufacturer;

@Repository
public interface ManufacturerRepository extends MongoRepository<Manufacturer,String> {
}
