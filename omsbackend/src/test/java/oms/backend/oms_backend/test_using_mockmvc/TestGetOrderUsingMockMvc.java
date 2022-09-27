package oms.backend.oms_backend.test_using_mockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import oms.backend.models.Order;
import oms.backend.oms_backend.OmsbackendApplication;
import oms.backend.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ContextConfiguration(classes = OmsbackendApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestGetOrderUsingMockMvc {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    OrderService orderService;

    @Test
    public void getAllOrders() throws Exception {

        List<Order> orderList = new ArrayList<>();

        Order order1 = new Order(1,1,1,10,1,"2011-12-18 13:17:17","2011-12-18 13:17:17", "Cancelled");
        Order order2 = new Order(2,2,2,20,2,"2011-12-18 13:17:17","2011-12-18 13:17:17", "Delivered");

        orderList.add(order1);
        orderList.add(order2);

        Mockito.when(orderService.getOrders()).thenReturn(orderList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/getOrders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].order_id", is(1)))
                .andExpect(jsonPath("$[1].order_id", is(2)))
                .andExpect(jsonPath("$[0].order_status", is("Cancelled")))
                .andExpect(jsonPath("$[1].order_status", is("Delivered")));
    }

    @Test
    void testGetOrderByInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/getOrders/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetOrderByValidId() throws Exception {
        Order order1 = new Order(1,1,1,10,1,"2011-12-18 13:17:17","2011-12-18 13:17:17", "Cancelled");

        Mockito.when(orderService.getOrderById(1)).thenReturn(Optional.of(order1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/getOrders/{order_id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderID", is(1)))
                .andExpect(jsonPath("orderStatus", is("Cancelled")));

    }
}
