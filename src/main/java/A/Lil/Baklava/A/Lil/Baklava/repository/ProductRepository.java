package A.Lil.Baklava.A.Lil.Baklava.repository;

import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    List<Product> getAllProducts();
    List<Product> findAll();
    Product getProductById(int id);


//    Product addProduct(Product product);
//
//
//
//
//    void deleteProductById(int id);
}

