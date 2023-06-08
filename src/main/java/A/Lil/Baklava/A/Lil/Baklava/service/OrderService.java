package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public Order createOrder(Order order) {
        orders.add(order);
        return order;
    }

    public Order updateOrder(int orderId, Order updatedOrder) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                order.setUserId(updatedOrder.getUserId());
                order.setProductId(updatedOrder.getProductId());
                order.setQuantity(updatedOrder.getQuantity());
                return order;
            }
        }
        return null;
    }

    public void deleteOrder(int orderId) {
        orders.removeIf(order -> order.getId() == orderId);
    }



}
