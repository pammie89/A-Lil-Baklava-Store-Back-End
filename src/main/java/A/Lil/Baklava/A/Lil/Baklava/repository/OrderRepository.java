package A.Lil.Baklava.A.Lil.Baklava.repository;

import A.Lil.Baklava.A.Lil.Baklava.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // You can add custom query methods here if needed
}
