package spring.boot.swiggyBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.database_model.Product;
import spring.boot.swiggyBE.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productName) {
        return productRepository.findById(productName);
    }

    public Product updateProduct(String productName, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productName);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setCategoryNo(updatedProduct.getCategoryNo());
            existingProduct.setProductNo(updatedProduct.getProductNo());
            existingProduct.setPrize(updatedProduct.getPrize());
            existingProduct.setManufacturer(updatedProduct.getManufacturer());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setStatus(updatedProduct.isStatus());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setCountry(updatedProduct.getCountry());
            existingProduct.setRating(updatedProduct.getRating());
            existingProduct.setCreatedOn(updatedProduct.getCreatedOn());
            existingProduct.setVegetarian(updatedProduct.isVegetarian());

            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    public void deleteProduct(String productName) {
        productRepository.deleteById(productName);
    }
}
