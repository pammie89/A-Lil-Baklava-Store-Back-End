package A.Lil.Baklava.A.Lil.Baklava.seed;

import A.Lil.Baklava.A.Lil.Baklava.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderDataLoader implements CommandLineRunner {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderDataLoader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //loadOrderData();
    }

//    private void loadOrderData() {
//        if (orderRepository.count() == 0) {
//            Order order1 = new Order(1, 1, 5);
//            Order order2 = new Order(2, 2, 10);
//            Order order3 = new Order(1, 3, 1);
//            orderRepository.save(order1);
//            orderRepository.save(order2);
//            orderRepository.save(order3);
//        }
//    }



}
