package A.Lil.Baklava.A.Lil.Baklava.seed;

import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import A.Lil.Baklava.A.Lil.Baklava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductDataLoader implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadProductData();
    }

    private void loadProductData() {
        if (productRepository.count() == 0) {
            Product product1 = new Product("A Lil Box", 25.00);
            Product product2 = new Product("The Half Box", 45.00);
            Product product3 = new Product("The Full Box", 80.00);
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);

        }

    }
}
