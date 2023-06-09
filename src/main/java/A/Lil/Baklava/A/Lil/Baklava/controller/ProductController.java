package A.Lil.Baklava.A.Lil.Baklava.controller;

        import A.Lil.Baklava.A.Lil.Baklava.model.Product;
        import A.Lil.Baklava.A.Lil.Baklava.service.ProductService;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    /**
     * Constructs an instance of ProductController with the given ProductService.
     *
     * @param productService The ProductService to be used for handling product-related operations.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product corresponding to the given ID.
     */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    /**
     * Adds a new product.
     *
     * @param product The product to be added.
     * @return The added product.
     */
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    /**
     * Updates an existing product.
     *
     * @param id             The ID of the product to update.
     * @param updatedProduct The updated product details.
     * @return A ResponseEntity with the updated product.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
