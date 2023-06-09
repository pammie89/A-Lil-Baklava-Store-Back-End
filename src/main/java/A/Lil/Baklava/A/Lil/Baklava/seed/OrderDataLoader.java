package A.Lil.Baklava.A.Lil.Baklava.seed;

import A.Lil.Baklava.A.Lil.Baklava.model.Order;
import A.Lil.Baklava.A.Lil.Baklava.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderDataLoader implements CommandLineRunner {

    private final OrderRepository orderRepository;
    /**
     * Constructs an instance of OrderDataLoader with the given OrderRepository.
     *
     * @param orderRepository The OrderRepository to be used for data access.
     */
    @Autowired
    public OrderDataLoader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Executes the data loading logic when the application starts.
     *
     * @param args The command line arguments.
     * @throws Exception if an error occurs during data loading.
     */
    @Override
    public void run(String... args) throws Exception {
        loadOrderData();
    }
    /**
     * Loads initial order data into the application if the order repository is empty.
     */
    private void loadOrderData() {
        if (orderRepository.count() == 0) {
            Order order1 = new Order(1L, 1, 5);
            Order order2 = new Order(2L, 2, 10);
            Order order3 = new Order(1L, 3, 1);
            orderRepository.save(order1);
            orderRepository.save(order2);
            orderRepository.save(order3);
        }
    }



}
