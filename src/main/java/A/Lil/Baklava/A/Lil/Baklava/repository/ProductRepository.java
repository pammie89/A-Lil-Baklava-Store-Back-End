package A.Lil.Baklava.A.Lil.Baklava.repository;

import A.Lil.Baklava.A.Lil.Baklava.model.Product;

import java.util.List;


public interface ProductRepository {
    List<Product> getAllProducts();

    Product getProductById(int id);

    Product addProduct(Product product);

    Product updateProduct(int id, Product updatedProduct);

    void deleteProduct(int id);
}

