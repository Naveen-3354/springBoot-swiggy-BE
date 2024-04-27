package spring.boot.swiggyBE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.components.generators.Generator;
import spring.boot.swiggyBE.http_model.request.Status;
import spring.boot.swiggyBE.database_model.Categories;
import spring.boot.swiggyBE.repository.CategoryRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final Generator generator;

    public ResponseEntity<String> addCategory(String id, Categories categories) {
        if (!categoryRepository.existsByCategoryName(categories.getCategoryName())) {
            categories.setCategoryNo(generator.categoryIdGenerator(categories.getCategoryName(),categoryRepository.count()));
            categories.setCreatedBy(id);
            categories.setCreatedOn(new Date());
            categories.setStatus(Status.ACTIVE);
            categoryRepository.save(categories);
            return ResponseEntity.ok("Category created sccessfully.");
        } else if (categoryRepository.existsByCategoryName(categories.getCategoryName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already found.");
        }
        return (ResponseEntity<String>) ResponseEntity.internalServerError();
    }

    public ResponseEntity<List<Categories>> getAllCategory(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    public ResponseEntity<List<Categories>> getAllByStatus(String status){
        Optional<Categories> categories =categoryRepository.findByStatus(Status.valueOf(status));
        if(categories.isPresent()){
            ResponseEntity.ok(categories.get());
        }
        return (ResponseEntity<List<Categories>>) ResponseEntity.notFound();
    }
}
