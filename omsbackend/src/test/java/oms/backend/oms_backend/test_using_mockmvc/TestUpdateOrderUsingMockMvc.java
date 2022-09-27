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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = OmsbackendApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUpdateOrderUsingMockMvc {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    OrderService orderService;

    @Test
    public void testUpdateOrderToShipped() throws Exception {
        Order order = new Order(1,1,1,10,1,"2011-12-18 13:17:17",null, "Pending");
        Mockito.when(orderService.updateOrder(1, "Shipped")).thenReturn(true);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/api/order/updateOrder/shipped/" + order.getOrderID()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(order));

        mockMvc.perform(builder).andExpect(status().isOk())
                        .andExpect(content().string(equalTo(order.getOrderID() + " Shipped successfully")));
    }

    @Test
    public void testUpdateOrderToCancelled() throws Exception {
        Order order = new Order(1,1,1,10,1,"2011-12-18 13:17:17",null, "Pending");
        Mockito.when(orderService.updateOrder(1, "Cancelled")).thenReturn(true);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/api/order/updateOrder/cancelled/" + order.getOrderID()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(order));

        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(content().string(equalTo(order.getOrderID() + " Cancelled successfully")));
    }
}
