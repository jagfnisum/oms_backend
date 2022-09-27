package oms.backend.oms_backend.test_using_mockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import oms.backend.models.OrderItems;
import oms.backend.oms_backend.OmsbackendApplication;
import oms.backend.services.OrderItemsSerivce;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = OmsbackendApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUpdateItemQuantityUsingMockMvc {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    OrderItemsSerivce orderItemsSerivce;

    @Test
    public void testUpdateQuantityInOrderWithValidOrderID() throws Exception {
        OrderItems orderItems = new OrderItems(1,6,35,"100000001111");

        int orderId =  orderItems.getOrder().getOrderID();
        String upc = orderItems.getUpc();
        int expectedUpdateQuantity = 50;

        Mockito.when(orderItemsSerivce.updateOrderItem(orderId, upc, expectedUpdateQuantity)).thenReturn(true);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .patch("/api/order/items/updateItemQuantity/" + orderId + "/" + upc)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(expectedUpdateQuantity));

        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Item " + upc + " updated to " + expectedUpdateQuantity)));
    }

    @Test
    public void testUpdateQuantityInOrderWithInvalidOrderID() throws Exception {
        OrderItems orderItems = new OrderItems(1,6,35,"100000001111");

        int orderId = orderItems.getOrderid();
        int randomOrderId = 3821;
        String upc = orderItems.getUpc();
        int expectedUpdateQuantity = 50;

        Mockito.when(orderItemsSerivce.updateOrderItem(orderId, upc, expectedUpdateQuantity)).thenReturn(true);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .patch("/api/order/items/updateItemQuantity/" + randomOrderId + "/" + upc)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.mapper.writeValueAsBytes(expectedUpdateQuantity));

        mockMvc.perform(builder).andExpect(status().isBadRequest());
    }

}
