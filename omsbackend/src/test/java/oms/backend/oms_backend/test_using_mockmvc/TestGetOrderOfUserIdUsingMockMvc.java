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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = OmsbackendApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestGetOrderOfUserIdUsingMockMvc {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    OrderService orderService;

    @Test
    public void TestGetOrderOfValidUserId() throws Exception {
        List<Order> orderList = new ArrayList<>();
        List<Order> orderListExpected = new ArrayList<>(orderList);

        Order order1 = new Order(1,1,1,10,1,"2011-12-18 13:17:17",null, "Cancelled");
        Order order2 = new Order(2,2,2,12,2,"2011-12-18 13:17:17","2011-12-18 13:17:17", "Shipped");
        Order order3 = new Order(3,1,1,15,1,"2011-12-18 13:17:17",null, "Pending");

        orderList.add(order1);
        orderList.add(order3);

        Mockito.when(orderService.getOrdersUserId(1)).thenReturn(orderList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/getOrders/user/{user_id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].orderID", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].orderStatus", is("Cancelled")))
                .andExpect(jsonPath("$[1].orderID", is(3)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].orderStatus", is("Pending")));

    }

}
