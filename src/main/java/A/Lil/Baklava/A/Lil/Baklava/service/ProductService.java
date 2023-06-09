package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.exception.InformationNotFoundException;
import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import A.Lil.Baklava.A.Lil.Baklava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {this.productRepository = productRepository;}

    /**
     * Constructs a new ProductService with the specified ProductRepository.
     *
     * @param productRepository The repository for accessing product data.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null); // Return the Product if it exists, or null if it doesn't
    }

    /**
     * Adds a new product.
     *
     * @param product The product to add.
     * @return The added product.
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id             The ID of the product to update.
     * @param updatedProduct The updated product information.
     * @return The updated product, or null if the product does not exist.
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        // Get the existing product by ID
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            // Update the existing product with the new values
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            // Save the updated product
            return productRepository.save(existingProduct);
        }
        return null; // Or handle the case where the product does not exist
    }
    /**
     * Deletes an existing product.
     *
     * @param id The ID of the product to delete.
     * @throws InformationNotFoundException if the product with the specified ID is not found.
     */
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new InformationNotFoundException("Product is not found with id " + id));
        productRepository.delete(product);
    }
}
