package spring.boot.swiggyBE.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.swiggyBE.database_model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String > {
}
