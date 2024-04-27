package spring.boot.swiggyBE.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import spring.boot.swiggyBE.http_model.request.Status;
import spring.boot.swiggyBE.database_model.Categories;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Categories, String> {
    boolean existsByCategoryName(String name);
    Optional<Categories> findByStatus(Status status);
}
