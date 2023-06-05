package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.exception.InformationNotFoundException;
import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import A.Lil.Baklava.A.Lil.Baklava.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public Product updateProduct(int productId, Product updatedProduct) {
        Product existingProduct = productRepository.getProductById(productId);
        if (existingProduct == null) {
            throw new InformationNotFoundException("Product not found with ID: " + productId);
        }

        // Update the fields of the existing product with the new values
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        // ...

        // Save the updated product in the repository
        Product savedProduct = productRepository.updateProduct(productId, existingProduct);

        return savedProduct;
    }
    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }
}
