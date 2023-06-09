package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.exception.InformationNotFoundException;
import A.Lil.Baklava.A.Lil.Baklava.model.Order;
import A.Lil.Baklava.A.Lil.Baklava.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private final List<Order> orders = new ArrayList<>();

    /**
     * Retrieves all orders from the repository.
     *
     * @return A list of all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The order with the specified ID, or null if not found.
     */
    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    /**
     * Creates a new order.
     *
     * @param order The order to create.
     * @return The created order.
     */
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Updates an existing order.
     *
     * @param orderId      The ID of the order to update.
     * @param updatedOrder The updated order information.
     * @return The updated order.
     * @throws InformationNotFoundException if the order with the specified ID is not found.
     */
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new InformationNotFoundException("Order is not found with id " + orderId));
        order.setUserId(updatedOrder.getUserId());
        order.setProductId(updatedOrder.getProductId());
        order.setQuantity(updatedOrder.getQuantity());
        return orderRepository.save(order);
    }
    /**
     * Deletes an existing order.
     *
     * @param orderId The ID of the order to delete.
     */
    public void deleteOrder(int orderId) {
        orders.removeIf(order -> order.getId() == orderId);
    }


}
