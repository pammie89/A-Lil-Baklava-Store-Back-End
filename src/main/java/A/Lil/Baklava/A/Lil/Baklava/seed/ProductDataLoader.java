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

    /**
     * Executes the data loading logic when the application starts.
     *
     * @param args The command line arguments.
     * @throws Exception if an error occurs during data loading.
     */
    @Override
    public void run(String... args) throws Exception {
        loadProductData();
    }

    /**
     * Loads initial product data into the application if the product repository is empty.
     */
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
