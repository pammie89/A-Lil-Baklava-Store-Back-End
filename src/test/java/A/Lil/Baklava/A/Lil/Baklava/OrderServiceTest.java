package A.Lil.Baklava.A.Lil.Baklava;

import A.Lil.Baklava.A.Lil.Baklava.controller.OrderController;
import A.Lil.Baklava.A.Lil.Baklava.model.Order;
import A.Lil.Baklava.A.Lil.Baklava.repository.OrderRepository;
import A.Lil.Baklava.A.Lil.Baklava.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
public class OrderServiceTest {
    private MockMvc mockMvc;

    @Mock
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, 1, 10));

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> foundOrders = orderService.getAllOrders();

        Assertions.assertEquals(orders.size(), 1);
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order(1L, 1, 10);
        Order updatedAttributes = new Order(2L, 2, 20);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        Order updatedOrder = orderService.updateOrder(1L, updatedAttributes);

        Assertions.assertEquals(updatedAttributes.getProductId(), updatedOrder.getProductId());
        Assertions.assertEquals(updatedAttributes.getQuantity(), updatedOrder.getQuantity());
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order(1L, 1, 10);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        Assertions.assertEquals(order.getId(), createdOrder.getId());
        Assertions.assertEquals(order.getProductId(), createdOrder.getProductId());
        Assertions.assertEquals(order.getQuantity(), createdOrder.getQuantity());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order updatedOrder = new Order(2L, 2, 5);

    }
}