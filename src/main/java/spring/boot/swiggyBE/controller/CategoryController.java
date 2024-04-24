package spring.boot.swiggyBE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.swiggyBE.database_model.Categories;
import spring.boot.swiggyBE.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add/{id}")
    public ResponseEntity<String> addCategory(@PathVariable String id,@RequestBody Categories categories){
        return categoryService.addCategory(id,categories);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categories>> getAll(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/all/{status}")
    public ResponseEntity<List<Categories>> getAllStatus(@PathVariable String status){
        return categoryService.getAllByStatus(status);
    }
}
