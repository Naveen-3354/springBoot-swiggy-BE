package spring.boot.swiggyBE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.swiggyBE.database_model.Product;
import spring.boot.swiggyBE.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create operation
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Read operations
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProductById(@PathVariable String productName) {
        Optional<Product> productOptional = productService.getProductById(productName);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update operation
    @PutMapping("/{productName}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productName, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(productName, updatedProduct);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productName}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productName) {
        productService.deleteProduct(productName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}