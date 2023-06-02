package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Integer, Product> products;

    public ProductService() {
        this.products = new HashMap<>();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(int productId) {
        return products.get(productId);
    }

    public Product addProduct(Product product) {
        products.put(product.getProductId(), product);
        return product;
    }

    public Product updateProduct(int productId, Product updatedProduct) {
        Product product = products.get(productId);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            products.put(productId, product);
        }
        return product;
    }

    public void deleteProduct(int productId) {
        products.remove(productId);
    }
}
