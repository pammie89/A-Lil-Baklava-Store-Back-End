package A.Lil.Baklava.A.Lil.Baklava.controller;

import A.Lil.Baklava.A.Lil.Baklava.model.Order;
import A.Lil.Baklava.A.Lil.Baklava.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    /**
     * Retrieves all orders.
     *
     * @return A list of all orders.
     */
    @GetMapping("")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The order corresponding to the given ID.
     */
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    /**
     * Creates a new order.
     *
     * @param order The order to be created.
     * @return The created order.
     */
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }


    /**
     * Updates an existing order.
     *
     * @param orderId The ID of the order to update.
     * @param order   The updated order details.
     * @return The updated order.
     */
    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId The ID of the order to delete.
     */
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }

}

