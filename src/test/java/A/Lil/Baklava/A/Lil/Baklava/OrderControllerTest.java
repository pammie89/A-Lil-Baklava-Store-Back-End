package A.Lil.Baklava.A.Lil.Baklava;

import A.Lil.Baklava.A.Lil.Baklava.controller.OrderController;
import A.Lil.Baklava.A.Lil.Baklava.model.Order;
import A.Lil.Baklava.A.Lil.Baklava.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetAllOrders() throws Exception {
        Order order1 = new Order(1, 1, 1, 5);
        Order order2 = new Order(2, 2, 2, 3);
        List<Order> orders = Arrays.asList(order1, order2);

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantity").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].productId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantity").value(3));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order(1, 1, 1, 5);

        when(orderService.getOrderById(1)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{orderId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(5));
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order(1, 1, 1, 5);
        Order createdOrder = new Order(1, 1, 1, 5);

        when(orderService.createOrder(any(Order.class))).thenReturn(createdOrder);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\": 1, \"userId\": 1, \"productId\": 1, \"quantity\": 5}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(5));
    }


    @Test
    public void testUpdateOrder() throws Exception {
        Order order = new Order(1, 1, 1, 5);
        Order updatedOrder = new Order(1, 1, 1, 10);

        when(orderService.updateOrder(1, any(Order.class))).thenReturn(updatedOrder);

        mockMvc.perform(MockMvcRequestBuilders.put("/orders/{orderId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderId\": 1, \"userId\": 1, \"productId\": 1, \"quantity\": 10}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10));
    }



}
